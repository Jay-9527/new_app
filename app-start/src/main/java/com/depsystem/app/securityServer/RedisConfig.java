/**
 * @author JOJO
 * @class RedisConfig
 * @date 2023/4/24
 * @apiNote
 */

package com.depsystem.app.securityServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    //@Bean(name = "myRedisTemplate")
    //public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
    //    //创建RedisTemplate对象
    //    RedisTemplate<String, Object> template = new RedisTemplate<>();
    //    //设置连接工厂
    //    template.setConnectionFactory(connectionFactory);
    //    //创建JSON序列化工具
    //    GenericJackson2JsonRedisSerializer jsonRedisSerializer=new GenericJackson2JsonRedisSerializer();
    //    //设置Key的序列化
    //    template.setKeySerializer(RedisSerializer.string());
    //    template.setHashKeySerializer(RedisSerializer.string());
    //    //设置value的序列化
    //    template.setValueSerializer(jsonRedisSerializer);
    //    template.setHashValueSerializer(jsonRedisSerializer);
    //    //返回
    //    return template;
    //}

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration controlRedis = new RedisStandaloneConfiguration();
        controlRedis.setDatabase(0);
        controlRedis.setPassword("adiao");
        controlRedis.setPort(6379);
        controlRedis.setHostName("47.113.147.38");
        return new LettuceConnectionFactory(controlRedis);
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    //@Bean("stringRedis")
    //public StringRedisTemplate stringRedisTemplate() {
    //    RedisConnectionFactory factory = new LettuceConnectionFactory("localhost", 6379);
    //    return new StringRedisTemplate(factory);
    //}


}
