# Redis 工具封装使用指南

## 概述

本项目为美食星球（MSXQ）后端提供了完整的Redis工具封装，包括配置、工具类、常量定义和示例服务。

## 目录结构

```
back-end/src/main/java/org/example/backend/
├── Config/
│   └── RedisConfig.java           # Redis配置类
└── Utils/
    ├── RedisUtil.java             # Redis基础工具类
    ├── RedisConstant.java         # Redis常量定义
    ├── RedisCacheService.java     # Redis缓存服务类
    └── RedisExampleService.java   # Redis使用示例
```

## 依赖配置

已在 `pom.xml` 中添加以下依赖：
- `spring-boot-starter-data-redis` - Spring Boot Redis支持
- `commons-pool2` - 连接池支持
- `jackson-databind` - JSON序列化
- `jackson-datatype-jsr310` - Java 8时间类型支持

## 配置文件

在 `application.yml` 中添加了Redis配置：
```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: 
    database: 0
    timeout: 3000ms
    lettuce:
      pool:
        max-active: 8
        max-idle: 8
        min-idle: 0
        max-wait: -1ms
```

## 核心组件说明

### 1. RedisConfig - 配置类
- 配置RedisTemplate，使用Jackson进行JSON序列化
- 支持Java 8时间类型
- 启用缓存注解支持

### 2. RedisUtil - 基础工具类
提供以下功能：
- **通用操作**：过期时间设置、key存在判断、删除等
- **String操作**：get/set、递增递减
- **Hash操作**：hget/hset、hmget/hmset等
- **Set操作**：sGet/sSet、集合运算等
- **List操作**：lGet/lSet、列表操作等
- **分布式锁**：tryLock/releaseLock

### 3. RedisConstant - 常量类
定义项目中使用的Redis key前缀和过期时间：
- 用户相关：`USER_TOKEN:`、`USER_INFO:` 等
- 验证码相关：`CAPTCHA_IMAGE:`、`CAPTCHA_SMS:` 等
- 美食相关：`FOOD_DETAIL:`、`FOOD_LIST:` 等
- AI相关：`AI_CHAT_HISTORY:`、`AI_RECOMMEND:` 等
- 分布式锁：`LOCK_USER_REGISTER:`、`LOCK_ORDER_CREATE:` 等

### 4. RedisCacheService - 缓存服务类
提供高级缓存功能：
- **缓存穿透保护**：getOrLoad方法，防止缓存穿透
- **缓存预热**：preheat和batchPreheat方法
- **缓存清理**：按前缀清理缓存
- **分布式锁高级方法**：带重试的锁执行
- **缓存统计**：命中率计算等

### 5. RedisExampleService - 示例服务类
展示如何在项目中使用Redis工具，包括：
- 用户token管理
- 美食详情缓存
- 验证码功能
- 登录保护
- 分布式锁使用
- 批量操作示例

## 快速开始

### 1. 基本使用

```java
@Service
public class YourService {
    
    @Autowired
    private RedisUtil redisUtil;
    
    public void example() {
        // 设置缓存
        redisUtil.set("key", "value", 60);
        
        // 获取缓存
        Object value = redisUtil.get("key");
        
        // 删除缓存
        redisUtil.del("key");
        
        // 判断key是否存在
        boolean exists = redisUtil.hasKey("key");
    }
}
```

### 2. 使用常量类

```java
// 使用常量构建key
String tokenKey = RedisConstant.buildKey(RedisConstant.USER_TOKEN_KEY, userId);
redisUtil.set(tokenKey, token, RedisConstant.USER_TOKEN_EXPIRE);

// 使用带参数的key构建
String foodListKey = RedisConstant.buildKeyWithParams(
    RedisConstant.FOOD_LIST_KEY, 
    category, page, size
);
```

### 3. 缓存穿透保护

```java
@Autowired
private RedisCacheService redisCacheService;

public Object getFoodDetail(String foodId) {
    String key = RedisConstant.buildKey(RedisConstant.FOOD_DETAIL_KEY, foodId);
    
    return redisCacheService.getOrLoad(key, RedisConstant.FOOD_DETAIL_EXPIRE, () -> {
        // 这里是从数据库加载数据的逻辑
        return foodMapper.selectById(foodId);
    });
}
```

### 4. 分布式锁使用

```java
public boolean createOrder(String orderId, String userId) {
    String lockKey = RedisConstant.buildKey(RedisConstant.LOCK_ORDER_CREATE, orderId);
    String requestId = UUID.randomUUID().toString();
    
    try {
        // 尝试获取锁
        boolean locked = redisUtil.tryLock(lockKey, requestId, 30);
        if (!locked) {
            return false; // 获取锁失败
        }
        
        try {
            // 执行订单创建逻辑
            return orderService.create(orderId, userId);
        } finally {
            // 释放锁
            redisUtil.releaseLock(lockKey, requestId);
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
```

## 最佳实践

### 1. 缓存key设计
- 使用有意义的key前缀
- 包含业务标识和参数
- 避免过长的key

### 2. 过期时间设置
- 根据数据更新频率设置合理的过期时间
- 热点数据可以设置较长的过期时间
- 验证码等临时数据设置较短的过期时间

### 3. 缓存穿透保护
- 使用`getOrLoad`方法防止缓存穿透
- 对不存在的key缓存空值标记
- 设置合理的空值缓存时间

### 4. 分布式锁使用
- 为每个锁设置唯一的requestId
- 设置合理的锁超时时间
- 确保在finally块中释放锁

### 5. 连接池配置
- 根据并发量调整连接池大小
- 监控连接池使用情况
- 设置合理的超时时间

## 常见问题

### 1. Redis连接失败
- 检查Redis服务是否启动
- 检查配置文件中的host和port
- 检查防火墙设置

### 2. 序列化问题
- 确保存储的对象可序列化
- 使用Jackson序列化复杂对象
- 注意循环引用问题

### 3. 内存使用过高
- 设置合理的过期时间
- 定期清理无用缓存
- 监控Redis内存使用情况

### 4. 性能问题
- 使用pipeline进行批量操作
- 避免大key和大value
- 合理使用数据结构

## 监控和维护

### 1. 监控指标
- 缓存命中率
- Redis内存使用率
- 连接池使用情况
- 命令执行时间

### 2. 维护建议
- 定期备份Redis数据
- 监控慢查询日志
- 定期清理过期key
- 升级Redis版本

## 扩展建议

### 1. 集群支持
- 配置Redis集群
- 使用Redis Sentinel
- 考虑数据分片

### 2. 缓存策略
- 实现多级缓存
- 使用缓存预热
- 实现缓存降级

### 3. 监控告警
- 集成监控系统
- 设置性能告警
- 实现自动扩缩容

## 总结

本Redis工具封装提供了完整的缓存解决方案，包括基础操作、高级功能和最佳实践。通过合理使用这些工具，可以显著提升系统性能和可靠性。