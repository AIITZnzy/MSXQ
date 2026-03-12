package org.example.backend.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Redis使用示例服务类
 * 展示如何在项目中使用Redis工具类
 */
@Service
public class RedisExampleService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisCacheService redisCacheService;

    // ============================== 用户相关示例 ==============================

    /**
     * 缓存用户token
     *
     * @param userId 用户ID
     * @param token  用户token
     */
    public void cacheUserToken(String userId, String token) {
        String key = RedisConstant.buildKey(RedisConstant.USER_TOKEN_KEY, userId);
        redisUtil.set(key, token, RedisConstant.USER_TOKEN_EXPIRE);
    }

    /**
     * 获取用户token
     *
     * @param userId 用户ID
     * @return 用户token
     */
    public String getUserToken(String userId) {
        String key = RedisConstant.buildKey(RedisConstant.USER_TOKEN_KEY, userId);
        Object token = redisUtil.get(key);
        return token != null ? token.toString() : null;
    }

    /**
     * 清除用户token
     *
     * @param userId 用户ID
     */
    public void clearUserToken(String userId) {
        String key = RedisConstant.buildKey(RedisConstant.USER_TOKEN_KEY, userId);
        redisUtil.del(key);
    }

    // ============================== 美食相关示例 ==============================

    /**
     * 缓存美食详情
     *
     * @param foodId 美食ID
     * @param foodDetail 美食详情
     */
    public void cacheFoodDetail(String foodId, Object foodDetail) {
        String key = RedisConstant.buildKey(RedisConstant.FOOD_DETAIL_KEY, foodId);
        redisUtil.set(key, foodDetail, RedisConstant.FOOD_DETAIL_EXPIRE);
    }

    /**
     * 获取美食详情（带缓存穿透保护）
     *
     * @param foodId 美食ID
     * @param loader 数据加载器
     * @return 美食详情
     */
    public Object getFoodDetailWithCache(String foodId, RedisCacheService.DataLoader<Object> loader) {
        String key = RedisConstant.buildKey(RedisConstant.FOOD_DETAIL_KEY, foodId);
        return redisCacheService.getOrLoad(key, RedisConstant.FOOD_DETAIL_EXPIRE, loader);
    }

    // ============================== 验证码相关示例 ==============================

    /**
     * 生成并缓存图片验证码
     *
     * @param uuid 验证码UUID
     * @param code 验证码
     */
    public void cacheImageCaptcha(String uuid, String code) {
        String key = RedisConstant.buildKey(RedisConstant.CAPTCHA_IMAGE_KEY, uuid);
        redisUtil.set(key, code, RedisConstant.CAPTCHA_EXPIRE);
    }

    /**
     * 验证图片验证码
     *
     * @param uuid 验证码UUID
     * @param code 用户输入的验证码
     * @return 是否验证成功
     */
    public boolean verifyImageCaptcha(String uuid, String code) {
        String key = RedisConstant.buildKey(RedisConstant.CAPTCHA_IMAGE_KEY, uuid);
        Object cachedCode = redisUtil.get(key);
        if (cachedCode == null) {
            return false;
        }
        boolean success = cachedCode.toString().equalsIgnoreCase(code);
        // 验证成功后删除验证码，防止重复使用
        if (success) {
            redisUtil.del(key);
        }
        return success;
    }

    // ============================== 登录保护示例 ==============================

    /**
     * 记录登录失败次数
     *
     * @param username 用户名
     * @return 当前失败次数
     */
    public long recordLoginFail(String username) {
        String key = RedisConstant.buildKey(RedisConstant.LOGIN_FAIL_COUNT_KEY, username);
        long count = redisUtil.incr(key, 1);
        // 设置过期时间，防止永久存储
        redisUtil.expire(key, RedisConstant.LOGIN_LOCK_EXPIRE);
        return count;
    }

    /**
     * 检查用户是否被锁定
     *
     * @param username 用户名
     * @param maxAttempts 最大尝试次数
     * @return 是否被锁定
     */
    public boolean isUserLocked(String username, int maxAttempts) {
        String countKey = RedisConstant.buildKey(RedisConstant.LOGIN_FAIL_COUNT_KEY, username);
        Object countObj = redisUtil.get(countKey);
        if (countObj == null) {
            return false;
        }
        long count = Long.parseLong(countObj.toString());
        return count >= maxAttempts;
    }

    /**
     * 重置登录失败次数
     *
     * @param username 用户名
     */
    public void resetLoginFail(String username) {
        String key = RedisConstant.buildKey(RedisConstant.LOGIN_FAIL_COUNT_KEY, username);
        redisUtil.del(key);
    }

    // ============================== 分布式锁示例 ==============================

    /**
     * 使用分布式锁创建订单
     *
     * @param orderId 订单ID
     * @param userId  用户ID
     * @return 是否创建成功
     */
    public boolean createOrderWithLock(String orderId, String userId) {
        String lockKey = RedisConstant.buildKey(RedisConstant.LOCK_ORDER_CREATE, orderId);
        String requestId = UUID.randomUUID().toString();

        try {
            // 尝试获取锁，超时时间30秒
            boolean locked = redisUtil.tryLock(lockKey, requestId, RedisConstant.LOCK_DEFAULT_EXPIRE);
            if (!locked) {
                // 获取锁失败，可能是其他线程正在创建订单
                return false;
            }

            try {
                // 执行订单创建逻辑
                // 这里可以添加实际的订单创建代码
                System.out.println("创建订单: " + orderId + " 用户: " + userId);
                return true;
            } finally {
                // 释放锁
                redisUtil.releaseLock(lockKey, requestId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ============================== 高级缓存示例 ==============================

    /**
     * 使用缓存服务获取数据
     *
     * @param foodId 美食ID
     * @return 美食详情
     */
    public Object getFoodDetailAdvanced(String foodId) {
        String key = RedisConstant.buildKey(RedisConstant.FOOD_DETAIL_KEY, foodId);
        
        return redisCacheService.getOrLoad(key, RedisConstant.FOOD_DETAIL_EXPIRE, () -> {
            // 这里是从数据库加载数据的逻辑
            // 示例：从数据库查询美食详情
            System.out.println("从数据库加载美食详情: " + foodId);
            
            // 模拟数据库查询结果
            Map<String, Object> foodDetail = new HashMap<>();
            foodDetail.put("id", foodId);
            foodDetail.put("name", "示例美食");
            foodDetail.put("description", "这是一个示例美食描述");
            foodDetail.put("price", 99.99);
            
            return foodDetail;
        });
    }

    // ============================== 缓存统计示例 ==============================

    /**
     * 模拟缓存访问统计
     */
    public void simulateCacheAccess() {
        String testKey = "TEST_CACHE_KEY";
        String testValue = "测试缓存值";
        
        // 设置缓存
        redisUtil.set(testKey, testValue, 60);
        
        // 检查缓存是否存在
        boolean exists = redisUtil.hasKey(testKey);
        System.out.println("缓存是否存在: " + exists);
        
        // 获取缓存
        Object cachedValue = redisUtil.get(testKey);
        System.out.println("缓存值: " + cachedValue);
        
        // 获取缓存剩余时间
        long remainingTime = redisUtil.getExpire(testKey);
        System.out.println("缓存剩余时间: " + remainingTime + "秒");
        
        // 刷新缓存时间
        redisUtil.expire(testKey, 120);
        System.out.println("刷新缓存时间后剩余: " + redisUtil.getExpire(testKey) + "秒");
        
        // 删除缓存
        redisUtil.del(testKey);
        System.out.println("删除缓存后是否存在: " + redisUtil.hasKey(testKey));
    }

    // ============================== 批量操作示例 ==============================

    /**
     * 批量缓存用户信息
     *
     * @param userInfos 用户信息Map，key为用户ID，value为用户信息
     */
    public void batchCacheUserInfo(Map<String, Object> userInfos) {
        for (Map.Entry<String, Object> entry : userInfos.entrySet()) {
            String key = RedisConstant.buildKey(RedisConstant.USER_INFO_KEY, entry.getKey());
            redisUtil.set(key, entry.getValue(), RedisConstant.DEFAULT_EXPIRE);
        }
    }

    /**
     * 批量获取用户信息
     *
     * @param userIds 用户ID列表
     * @return 用户信息Map
     */
    public Map<String, Object> batchGetUserInfo(Iterable<String> userIds) {
        Map<String, Object> result = new HashMap<>();
        for (String userId : userIds) {
            String key = RedisConstant.buildKey(RedisConstant.USER_INFO_KEY, userId);
            Object userInfo = redisUtil.get(key);
            if (userInfo != null) {
                result.put(userId, userInfo);
            }
        }
        return result;
    }
}