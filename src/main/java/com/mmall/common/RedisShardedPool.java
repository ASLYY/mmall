package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;

public class RedisShardedPool {

    /**sharded Jedis连接池**/
    private static ShardedJedisPool pool;

    /**最大连接数**/
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total","20"));

    /**在jedispool中最大的idle状态（空闲状态）的jedis实例的个数**/
    private static Integer miaxIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle","10"));

    /**在jedispool中最小的idle状态（空闲状态）的jedis实例的个数**/
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle","2"));

    /**在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。**/
    private static boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow","true"));

    /**在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。**/
    private static boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return","true"));

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));

    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));


    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        //连接耗尽的时候是否阻塞 false会抛出异常，true阻塞指导超时，默认为true
        config.setBlockWhenExhausted(true);

        JedisShardInfo info = new JedisShardInfo(redisIp,redisPort,1000*2);
        JedisShardInfo info1 = new JedisShardInfo(redis1Ip,redis1Port,1000*2);

        List<JedisShardInfo> jedisShardInfoList = new ArrayList<JedisShardInfo>(2);
        jedisShardInfoList.add(info);
        jedisShardInfoList.add(info1);

        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }

    public static void returnBrokenResource(ShardedJedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void main(String[] args) {
        ShardedJedis shardedJedis = pool.getResource();
//        shardedJedis.set("geelykey","geelyvalue");
        for (int i=0; i<10; i++) {
            shardedJedis.set("key"+i,"value"+i);
        }
        returnResource(shardedJedis);
//        pool.destroy();
        //临时调用，销毁连接池中的所有连接
        System.out.println("program is end");
    }

}
