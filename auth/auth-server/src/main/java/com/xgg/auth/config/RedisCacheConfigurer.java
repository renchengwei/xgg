package com.xgg.auth.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 使用fastjson序列表替换jdk序列化方式，增加可读性
 * 按照弃用此方案，接口类型反序列会有问题
 * @author renchengwei
 * @date 2019-11-23
 */
//@Configuration
public class RedisCacheConfigurer {

//    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new FastJsonRedisSerializer<>(Object.class)));
    }


}
