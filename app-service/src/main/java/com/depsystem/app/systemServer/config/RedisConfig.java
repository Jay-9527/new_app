package com.depsystem.app.systemServer.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,Object> redisTemplate (RedisConnectionFactory redisConnectionFactory)throws UnknownHostException {
        RedisTemplate<String,Object> redis = new RedisTemplate<>();
        redis.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  这个被弃用了，所以现在要用 activateDefaultTyping
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        redis.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        redis.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        redis.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        redis.setHashValueSerializer(jackson2JsonRedisSerializer);
        redis.afterPropertiesSet();
        return redis;
    }
}
