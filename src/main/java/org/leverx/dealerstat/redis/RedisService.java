package org.leverx.dealerstat.redis;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public interface RedisService {
    void save(Object key, Object value, Integer time, TimeUnit timeUnit);

    Object findByKey(Object key);

    boolean contains(Object key);

    void delete(Object key);

}
