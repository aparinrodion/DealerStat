package org.leverx.dealerstat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan("org.leverx.dealerstat")
@PropertySource({"classpath:application.properties"})
public class RedisConfig {
    @Value("${redis.host.name}")
    private String hostName;
    @Value("${redis.port}")
    private Integer port;

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration =
                new RedisStandaloneConfiguration(hostName, port);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<Object, Object> template() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer( new GenericToStringSerializer<>(Object.class) );
        template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class) );
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class) );
        template.setEnableTransactionSupport(true);
        return template;
    }


}
