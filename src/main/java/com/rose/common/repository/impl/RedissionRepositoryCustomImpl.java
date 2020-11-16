package com.rose.common.repository.impl;

import com.rose.common.repository.RedissionRepositoryCustom;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@Repository
public class RedissionRepositoryCustomImpl implements RedissionRepositoryCustom {

    @Inject
    private RedissonClient redissonClient;

    @Override
    public boolean tryLock(String lockKey, int waitTime, int leaseTime) {
        RLock rLock = redissonClient.getLock(lockKey);
        boolean getLock;
        try {
            getLock = rLock.tryLock( waitTime,leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return getLock;
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }
}