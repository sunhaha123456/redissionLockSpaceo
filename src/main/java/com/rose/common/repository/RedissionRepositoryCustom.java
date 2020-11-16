package com.rose.common.repository;

public interface RedissionRepositoryCustom {

    boolean tryLock(String lockKey, int waitTime, int leaseTime);

    void unlock(String lockKey);
}
