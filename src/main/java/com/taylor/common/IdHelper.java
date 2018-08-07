package com.taylor.common;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;


import static java.time.format.DateTimeFormatter.ofPattern;
import static jodd.util.StringPool.EMPTY;

/**
 * @author jearton
 * @since 2017/2/7
 */
public  class IdHelper {

    public static final UUID ZERO_UUID = new UUID(0, 0);

    private static volatile IdGenerator idGenerator;

    /**
     * 全局分布式唯一序号
     */
    public static String getNo(ChronoUnit truncatedTo, String custom) {
        _init();
        return idGenerator.nextNo(truncatedTo, custom);
    }

    /**
     * 全局分布式唯一序号
     */
    public static String getNo(String custom) {
        _init();
        return idGenerator.nextNo(custom);
    }

    /**
     * 全局分布式唯一序号
     */
    public static String getNo() {
        return getNo(EMPTY);
    }

    /**
     * 全局分布式唯一ID
     */
    public static long getId() {
        _init();
        return idGenerator.nextId();
    }

    /**
     * 32位UUID
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成唯一键中的时间戳，默认格式：yyMMddHHmmssSSS
     */
    public static String getTimeToken() {
        return ofPattern("yyMMddHHmmssSSS").format(ZonedDateTime.now());
    }

    /**
     * 获取ID生成器
     */
    public static IdGenerator getGenerator() {
        _init();
        return idGenerator;
    }

    /**
     * 重设参数
     */
    public static synchronized void reload(Integer nodeId, Long baseTimestamp, Integer sequenceBits) {
        if (nodeId == null && baseTimestamp == null && sequenceBits == null) {
            return;
        }
        idGenerator = new IdGenerator(nodeId, baseTimestamp, sequenceBits);
    }

    private static synchronized void _init() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
        }
    }
}
