package org.example.backend.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis缓存服务类
 * 提供更高级的缓存操作方法
 */
@Service
public class RedisCacheService {

    @Autowired
    private RedisUtil redisUtil;

    // ============================== 缓存穿透保护 ==============================

    /**
     * 获取缓存，如果不存在则从数据库加载并缓存
     *
     * @param key         缓存key
     * @param expireTime  过期时间(秒)
     * @param loader      数据加载器
     * @param <T>         数据类型
     * @return 数据
     */
    public <T> T getOrLoad(String key, long expireTime, DataLoader<T> loader) {
        // 尝试从缓存获取
        Object cached = redisUtil.get(key);
        if (cached != null) {
            // 如果是空值标记，返回null
            if (cached instanceof String && "NULL".equals(cached)) {
                return null;
            }
            return (T) cached;
        }

        // 缓存不存在，从数据库加载
        T data = loader.load();
        if (data != null) {
            // 数据存在，缓存数据
            redisUtil.set(key, data, expireTime);
        } else {
            // 数据不存在，缓存空值标记，防止缓存穿透
            redisUtil.set(key, "NULL", 60); // 空值缓存1分钟
        }
        return data;
    }

    /**
     * 数据加载器接口
     */
    @FunctionalInterface
    public interface DataLoader<T> {
        T load();
    }

    // ============================== 缓存预热 ==============================

    /**
     * 预热缓存
     *
     * @param key         缓存key
     * @param value       缓存值
     * @param expireTime  过期时间(秒)
     */
    public void preheat(String key, Object value, long expireTime) {
        redisUtil.set(key, value, expireTime);
    }

    /**
     * 批量预热缓存
     *
     * @param keyPrefix   key前缀
     * @param values      值列表
     * @param keyExtractor key提取器
     * @param expireTime  过期时间(秒)
     * @param <T>         数据类型
     */
    public <T> void batchPreheat(String keyPrefix, Iterable<T> values, KeyExtractor<T> keyExtractor, long expireTime) {
        for (T value : values) {
            String key = keyPrefix + keyExtractor.extract(value);
            redisUtil.set(key, value, expireTime);
        }
    }

    /**
     * key提取器接口
     */
    @FunctionalInterface
    public interface KeyExtractor<T> {
        String extract(T value);
    }

    // ============================== 缓存清理 ==============================

    /**
     * 清除指定前缀的所有缓存
     *
     * @param keyPrefix key前缀
     */
    public void clearByPrefix(String keyPrefix) {
        // 注意：在生产环境中，建议使用SCAN命令而不是KEYS命令
        // 这里简化处理，实际项目中应该使用RedisTemplate的scan方法
        // redisUtil.del(keys...);
    }

    /**
     * 清除用户相关缓存
     *
     * @param userId 用户ID
     */
    public void clearUserCache(String userId) {
        // 清除用户token
        redisUtil.del(RedisConstant.buildKey(RedisConstant.USER_TOKEN_KEY, userId));
        // 清除用户信息
        redisUtil.del(RedisConstant.buildKey(RedisConstant.USER_INFO_KEY, userId));
        // 清除用户笔记缓存
        // 这里可以添加更多用户相关缓存的清理逻辑
    }

    // ============================== 缓存统计 ==============================

    /**
     * 获取缓存命中率
     *
     * @param hitCount  命中次数
     * @param totalCount 总访问次数
     * @return 命中率
     */
    public double getHitRate(long hitCount, long totalCount) {
        if (totalCount == 0) {
            return 0.0;
        }
        return (double) hitCount / totalCount;
    }

    // ============================== 分布式锁高级方法 ==============================

    /**
     * 使用分布式锁执行任务
     *
     * @param lockKey     锁key
     * @param requestId   请求标识
     * @param expireTime  锁过期时间(秒)
     * @param task        要执行的任务
     * @param <T>         返回值类型
     * @return 任务执行结果
     * @throws InterruptedException 中断异常
     */
    public <T> T executeWithLock(String lockKey, String requestId, long expireTime, LockTask<T> task) throws InterruptedException {
        // 尝试获取锁
        boolean locked = false;
        try {
            locked = redisUtil.tryLock(lockKey, requestId, expireTime);
            if (locked) {
                // 获取锁成功，执行任务
                return task.execute();
            } else {
                // 获取锁失败，等待重试
                Thread.sleep(100); // 等待100ms
                return null;
            }
        } finally {
            // 释放锁
            if (locked) {
                redisUtil.releaseLock(lockKey, requestId);
            }
        }
    }

    /**
     * 带重试的分布式锁执行任务
     *
     * @param lockKey     锁key
     * @param requestId   请求标识
     * @param expireTime  锁过期时间(秒)
     * @param maxRetries  最大重试次数
     * @param retryDelay  重试延迟(毫秒)
     * @param task        要执行的任务
     * @param <T>         返回值类型
     * @return 任务执行结果
     * @throws InterruptedException 中断异常
     */
    public <T> T executeWithLockRetry(String lockKey, String requestId, long expireTime, 
                                      int maxRetries, long retryDelay, LockTask<T> task) throws InterruptedException {
        for (int i = 0; i < maxRetries; i++) {
            T result = executeWithLock(lockKey, requestId, expireTime, task);
            if (result != null) {
                return result;
            }
            if (i < maxRetries - 1) {
                Thread.sleep(retryDelay);
            }
        }
        return null;
    }

    /**
     * 锁任务接口
     */
    @FunctionalInterface
    public interface LockTask<T> {
        T execute();
    }

    // ============================== 缓存工具方法 ==============================

    /**
     * 判断缓存是否有效
     *
     * @param key 缓存key
     * @return 是否有效
     */
    public boolean isCacheValid(String key) {
        return redisUtil.hasKey(key);
    }

    /**
     * 刷新缓存过期时间
     *
     * @param key        缓存key
     * @param expireTime 新的过期时间(秒)
     * @return 是否成功
     */
    public boolean refreshExpire(String key, long expireTime) {
        return redisUtil.expire(key, expireTime);
    }

    /**
     * 获取缓存剩余时间
     *
     * @param key 缓存key
     * @return 剩余时间(秒)
     */
    public long getRemainingTime(String key) {
        return redisUtil.getExpire(key);
    }
}