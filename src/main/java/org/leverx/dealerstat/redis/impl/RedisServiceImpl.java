package org.leverx.dealerstat.redis.impl;

import lombok.RequiredArgsConstructor;
import org.leverx.dealerstat.redis.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(Object key, Object value, Integer time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    @Override
    public Object findByKey(Object key) {
        if (contains(key)) {
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    @Override
    public boolean contains(Object key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void delete(Object key) {
        if (contains(key)) {
            redisTemplate.delete(key);
        }
    }


}
