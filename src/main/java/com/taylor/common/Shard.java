package com.taylor.common;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.*;

/**
 * S类封装了机器节点的信息 ，如name、password、ip、port等
 **/
public class Shard<S> {
    /**
     * 虚拟节点
     **/
    private TreeMap<Long, S> nodes;
    /**
     * 真实机器节点
     **/
    private List<S> shards;

    public Shard(List<S> shards, int virtualNumer) {
        this.shards = shards;
        init(virtualNumer);
    }

    /**
     * 初始化一致性hash环
     **/
    private void init(int virtualNumer) {
        nodes = new TreeMap<>();
        /**每个真实机器节点都需要关联虚拟节点**/
        for (int i = 0; i != shards.size(); i++) {
            final S shardInfo = shards.get(i);
            for (int n = 0; n < virtualNumer; n++)
            /** 一个真实机器节点关联NODE_NUM个虚拟节点**/ {
                nodes.put(hash("SHARD-" + i + "-NODE-" + n), shardInfo);
            }

        }
    }

    public S getShardInfo(String key) {
        // 沿环的顺时针找到一个虚拟节点
        SortedMap<Long, S> tail = nodes.tailMap(hash(key));
        if (tail.isEmpty()) {
            return nodes.get(nodes.firstKey());
        }
        // 返回该虚拟节点对应的真实机器节点的信息
        return tail.get(tail.firstKey());
    }

    /**
     * MurMurHash算法，是非加密HASH算法，性能很高， 比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
     * 等HASH算法要快很多，而且据说这个算法的碰撞率很低. http://murmurhash.googlepages.com/
     */
    private Long hash(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        int seed = 0x1234ABCD;
        long h = seed ^ (buf.remaining() * m);

        while (buf.remaining() >= 8) {
            long k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    public static void main(String... args) {
        List<Boolean> list = Arrays.asList(Boolean.TRUE, Boolean.FALSE);
        Shard<Boolean> share = new Shard<>(list, 1000000);
        SecureRandom secureRandom = new SecureRandom();
        int countFalse = 0;
        int countTrue = 0;
        for (int i = 0; i < 10000; i++) {
            Boolean b=share.getShardInfo(UUID.randomUUID().toString());
            System.out.println(b);
            if (b) {
                countFalse++;
            } else {
                countTrue++;
            }
        }
        System.out.println("True:"+countTrue);
        System.out.println("False:"+countFalse);
    }

}