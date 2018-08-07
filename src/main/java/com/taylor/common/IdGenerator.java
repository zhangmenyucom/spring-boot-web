package com.taylor.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.format;
import static java.time.ZoneId.systemDefault;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * 分布式ID生成器
 * Created by Jearton on 2016/12/17.
 */
@Slf4j
public class IdGenerator {
    //机器号10位
    private static final int NODE_ID_BITS = 10;
    //机器号最大值：1023
    private static final int MAX_NODE_ID = ~(-1 << NODE_ID_BITS);

    private final ReentrantLock lock = new ReentrantLock();
    private int sequence;
    private long lastTimestamp = -1L;

    @Getter
    //基准时间戳
    private final long baseTimestamp;
    //机器号
    private final int nodeId;
    //序列号位数
    private final int sequenceBits;
    //序列号最大值
    private final int sequenceMask;
    //时间毫秒数左移位数
    @Getter
    private final int timestampLeftShift;

    public IdGenerator() {
        this(null);
    }

    public IdGenerator(Integer nodeId) {
        this(nodeId, null, null);
    }

    public IdGenerator(Long baseTimestamp, Integer sequenceBits) {
        this(null, baseTimestamp, sequenceBits);
    }

    public IdGenerator(Integer nodeId, Long baseTimestamp, Integer sequenceBits) {
        //默认值
        //基准时间戳：2010-11-04T09:42:54.657+08:00[Asia/Shanghai]
        if (nodeId == null) {
            nodeId = getDefaultNodeId();
        }
        if (baseTimestamp == null) {
            baseTimestamp = 1288834974657L;
        }
        if (sequenceBits == null) {
            sequenceBits = 12;
        }

        //校验
        if (nodeId > MAX_NODE_ID || nodeId < 0) {
            throw new IllegalArgumentException(format("node Id can't be greater than %d or less than 0", MAX_NODE_ID));
        }
        if (sequenceBits > 12 || sequenceBits < 0) {
            throw new IllegalArgumentException(format("sequence bits can't be greater than %d or less than 0", 12));
        }
        if (baseTimestamp > currentTimeMillis()) {
            throw new IllegalArgumentException("base timestamp can't be greater than now");
        }

        //初始化
        this.baseTimestamp = baseTimestamp;
        this.nodeId = nodeId;
        this.sequenceBits = sequenceBits;
        this.sequenceMask = ~(-1 << sequenceBits);
        this.timestampLeftShift = NODE_ID_BITS + sequenceBits;
    }

    public String nextNo(String custom) {
        return nextNo(DAYS, custom);
    }

    public String nextNo(ChronoUnit truncatedTo, String custom) {
        lock.lock();
        try {
            //更新时间戳和序列号
            _updateTimestampAndSequence();

            //基量时间戳
            ZonedDateTime zonedDateTime = Instant.ofEpochMilli(lastTimestamp).atZone(systemDefault());
            long baseTimestamp = zonedDateTime.truncatedTo(truncatedTo).toInstant().toEpochMilli();

            //时间戳增量
            long timestampInc = lastTimestamp - baseTimestamp;

            // 000000000000000000000000000000000000000000       0000000000       000000000000
            // timestamp(41b)                                   nodeId(10b)      sequence
            long suffix = (timestampInc << timestampLeftShift) | (nodeId << sequenceBits) | sequence;

            //时间前缀
            String timePrefix;
            switch (truncatedTo) {
                case DAYS:
                    timePrefix = DateTimeFormatter.ofPattern("yyyyMMdd").format(zonedDateTime);
                    break;
                case HOURS:
                    timePrefix = DateTimeFormatter.ofPattern("yyyyMMddHH").format(zonedDateTime);
                    break;
                case MINUTES:
                    timePrefix = DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(zonedDateTime);
                    break;
                case SECONDS:
                    timePrefix = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(zonedDateTime);
                    break;
                default:
                    throw new Exception("truncatedTo[" + truncatedTo + "] cannot not support");
            }
            return timePrefix + custom + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return "";
    }

    public long nextId() {
        lock.lock();
        try {
            //更新时间戳和序列号
            _updateTimestampAndSequence();

            //时间戳增量
            long timestampInc = lastTimestamp - baseTimestamp;

            // 000000000000000000000000000000000000000000       0000000000       000000000000
            // timestamp(41b)                                   nodeId(10b)      sequence
            return (timestampInc << timestampLeftShift) | (nodeId << sequenceBits) | sequence;
        } finally {
            lock.unlock();
        }
    }

    private void _updateTimestampAndSequence() {
        //获取当前毫秒数
        long timestamp = currentTimeMillis();
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同，在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            //自旋等待到下一毫秒
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //如果和上次生成时间不同，重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
            sequence = 0;
        }
        lastTimestamp = timestamp;
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimeMillis();
        }
        return timestamp;
    }

    private static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    private static int getDefaultNodeId() {
        try {
            int ipAddressAsInt = Inets.fetchLocalIpAsInt(1);
            return Math.abs(ipAddressAsInt) & MAX_NODE_ID;
        } catch (Exception e) {
            log.error("nodeId initialized error", e);
            return 0;
        }
    }
}
