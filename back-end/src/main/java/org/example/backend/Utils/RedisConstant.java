package org.example.backend.Utils;

/**
 * Redis常量类
 * 定义项目中使用的Redis key前缀
 */
public class RedisConstant {

    // ============================== 用户相关 ==============================
    /**
     * 用户token前缀
     * 格式: USER_TOKEN:{userId}
     */
    public static final String USER_TOKEN_KEY = "USER_TOKEN:";

    /**
     * 用户信息前缀
     * 格式: USER_INFO:{userId}
     */
    public static final String USER_INFO_KEY = "USER_INFO:";

    /**
     * 用户登录失败次数前缀
     * 格式: LOGIN_FAIL_COUNT:{username}
     */
    public static final String LOGIN_FAIL_COUNT_KEY = "LOGIN_FAIL_COUNT:";

    /**
     * 用户登录锁定前缀
     * 格式: LOGIN_LOCK:{username}
     */
    public static final String LOGIN_LOCK_KEY = "LOGIN_LOCK:";

    // ============================== 验证码相关 ==============================
    /**
     * 图片验证码前缀
     * 格式: CAPTCHA_IMAGE:{uuid}
     */
    public static final String CAPTCHA_IMAGE_KEY = "CAPTCHA_IMAGE:";

    /**
     * 短信验证码前缀
     * 格式: CAPTCHA_SMS:{phone}
     */
    public static final String CAPTCHA_SMS_KEY = "CAPTCHA_SMS:";

    /**
     * 邮箱验证码前缀
     * 格式: CAPTCHA_EMAIL:{email}
     */
    public static final String CAPTCHA_EMAIL_KEY = "CAPTCHA_EMAIL:";

    // ============================== 美食相关 ==============================
    /**
     * 美食详情前缀
     * 格式: FOOD_DETAIL:{foodId}
     */
    public static final String FOOD_DETAIL_KEY = "FOOD_DETAIL:";

    /**
     * 美食列表前缀
     * 格式: FOOD_LIST:{category}:{page}:{size}
     */
    public static final String FOOD_LIST_KEY = "FOOD_LIST:";

    /**
     * 热门美食前缀
     * 格式: FOOD_HOT:{limit}
     */
    public static final String FOOD_HOT_KEY = "FOOD_HOT:";

    // ============================== AI相关 ==============================
    /**
     * AI对话历史前缀
     * 格式: AI_CHAT_HISTORY:{sessionId}
     */
    public static final String AI_CHAT_HISTORY_KEY = "AI_CHAT_HISTORY:";

    /**
     * AI推荐结果前缀
     * 格式: AI_RECOMMEND:{userId}
     */
    public static final String AI_RECOMMEND_KEY = "AI_RECOMMEND:";

    // ============================== 笔记相关 ==============================
    /**
     * 用户笔记前缀
     * 格式: USER_NOTES:{userId}:{page}:{size}
     */
    public static final String USER_NOTES_KEY = "USER_NOTES:";

    /**
     * 笔记详情前缀
     * 格式: NOTE_DETAIL:{noteId}
     */
    public static final String NOTE_DETAIL_KEY = "NOTE_DETAIL:";

    // ============================== 缓存配置 ==============================
    /**
     * 默认过期时间（秒）
     */
    public static final long DEFAULT_EXPIRE = 3600L; // 1小时

    /**
     * 用户token过期时间（秒）
     */
    public static final long USER_TOKEN_EXPIRE = 7 * 24 * 3600L; // 7天

    /**
     * 验证码过期时间（秒）
     */
    public static final long CAPTCHA_EXPIRE = 300L; // 5分钟

    /**
     * 登录失败锁定时间（秒）
     */
    public static final long LOGIN_LOCK_EXPIRE = 1800L; // 30分钟

    /**
     * 美食详情缓存时间（秒）
     */
    public static final long FOOD_DETAIL_EXPIRE = 1800L; // 30分钟

    /**
     * 美食列表缓存时间（秒）
     */
    public static final long FOOD_LIST_EXPIRE = 600L; // 10分钟

    /**
     * AI对话历史缓存时间（秒）
     */
    public static final long AI_CHAT_HISTORY_EXPIRE = 24 * 3600L; // 24小时

    // ============================== 分布式锁 ==============================
    /**
     * 用户注册锁前缀
     * 格式: LOCK_USER_REGISTER:{username}
     */
    public static final String LOCK_USER_REGISTER = "LOCK_USER_REGISTER:";

    /**
     * 订单创建锁前缀
     * 格式: LOCK_ORDER_CREATE:{orderId}
     */
    public static final String LOCK_ORDER_CREATE = "LOCK_ORDER_CREATE:";

    /**
     * 库存扣减锁前缀
     * 格式: LOCK_STOCK_DECREASE:{productId}
     */
    public static final String LOCK_STOCK_DECREASE = "LOCK_STOCK_DECREASE:";

    /**
     * 分布式锁默认超时时间（秒）
     */
    public static final long LOCK_DEFAULT_EXPIRE = 30L; // 30秒

    // ============================== 工具方法 ==============================
    
    /**
     * 构建完整的Redis key
     * @param prefix key前缀
     * @param suffix key后缀
     * @return 完整的key
     */
    public static String buildKey(String prefix, String suffix) {
        return prefix + suffix;
    }

    /**
     * 构建带参数的Redis key
     * @param prefix key前缀
     * @param params 参数数组
     * @return 完整的key
     */
    public static String buildKeyWithParams(String prefix, Object... params) {
        StringBuilder key = new StringBuilder(prefix);
        for (Object param : params) {
            key.append(param).append(":");
        }
        // 移除最后一个冒号
        if (key.length() > 0 && key.charAt(key.length() - 1) == ':') {
            key.deleteCharAt(key.length() - 1);
        }
        return key.toString();
    }
}