package com.epam.AuthenticationAndAuthorization.service;

import com.epam.AuthenticationAndAuthorization.model.Protector;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {
    public static final int MAX_ATTEMPT = 1;
    public static final int BLOCK_DURATION_PER_MINUTES = 2;

    private final LoadingCache<String, Protector> attemptsCache;

    public LoginAttemptService() {
        attemptsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(BLOCK_DURATION_PER_MINUTES, TimeUnit.MINUTES)
                .build(new CacheLoader<>() {
                    @Override
                    public Protector load(String s) throws Exception {
                        return new Protector(0, LocalDateTime.now());
                    }
                });
    }

    public void loginFailed(final String key) {
        Protector protector = new Protector();
        try {
            protector = attemptsCache.get(key);
            protector.setAttempts(protector.getAttempts() + 1);
        } catch (ExecutionException e) {
            protector.setAttempts(0);
        }

        if (isBlocked(key) && protector.getBlockedTimestamp() == null) {
            protector.setBlockedTimestamp(LocalDateTime.now());
        }
        attemptsCache.put(key, protector);
    }

    public boolean isBlocked(String key) {

        try {
            return attemptsCache.get(key).getAttempts() >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }

    public Protector getCachedValue(String key) {
        return attemptsCache.getUnchecked(key);
    }
}
