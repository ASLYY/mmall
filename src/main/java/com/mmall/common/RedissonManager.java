package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class RedissonManager {

    private Config config = new Config();
    private Redisson redisson = null;

    public Redisson getRedisson() {
        return redisson;
    }

    private static String redisIp = PropertiesUtil.getProperty("redis.ip");
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.getProperty("redis.port"));

    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));


//    在构造器完成之后编译init
    @PostConstruct
    private void init() {

//        setAddress参数使用 ip:port 形式
        try {
            config.useSingleServer().setAddress(new StringBuilder().append(redisIp).append(":").append(redisPort).toString());
            redisson = (Redisson) Redisson.create(config);
            log.info("redisson初始化结束");
        } catch (Exception e) {
            log.info("redisson init error",e);
        }


    }



}
