package com.haydikodlayalim.redisapp.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration //Spring e bir konfigurasyon dosyası olduğunu belirtiyoruz.
@EnableCaching //Spring tarafınfa caching mekanizmasını devreye almak için kullandığımız anatasyon
public class AppConfiguration {

    @Bean //jedisConnectionFactory metodu içerisindeki dönen instance ı IOC içersine eklemek için @bean anatasyonunu kullanıyoruz.
    public JedisConnectionFactory jedisConnectionFactory() { //Redis e bağlanmamızı sağlayan connection nesnemiz.İhtiyaç duyduğumuz da bize connection nesnesi veren bir factory.
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean//IOC ye instance ı ekledik.@bean ile
    public RedisTemplate redisTemplate() { //Cache işlemlerini RedisTemplate yapısı üzerinde yapacağız.
        RedisTemplate template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

}
