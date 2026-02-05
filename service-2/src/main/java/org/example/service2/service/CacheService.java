package org.example.service2.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CacheService {
    private final StringRedisTemplate redisTemplate;

    public CacheService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String get(String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value;
    }

    public void set(String key, String value, long ttlSeconds) {
        redisTemplate.opsForValue()
                .set(key, value, Duration.ofSeconds(ttlSeconds));
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
