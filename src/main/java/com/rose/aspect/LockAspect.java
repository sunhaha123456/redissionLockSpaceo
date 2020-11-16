package com.rose.aspect;

import com.rose.aspect.annotation.Lock;
import com.rose.common.exception.BusinessException;
import com.rose.common.repository.RedissionRepositoryCustom;
import com.rose.common.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Aspect
@Component
public class LockAspect {

    @Inject
    private RedissionRepositoryCustom redissionRepositoryCustom;

    @Around("@annotation(lock)")
    public void around(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        String lockName = lock.value();
        int lessTime = lock.leaseTime();

        try {
            if (StringUtil.isEmpty(lockName)) {
                CodeSignature signature = (CodeSignature) joinPoint.getSignature();
                lockName = signature.getName();
            }

            boolean tryLockFlag = redissionRepositoryCustom.tryLock(lockName, 3000, lessTime);
            if (tryLockFlag) {
                joinPoint.proceed();
            } else {
                throw new BusinessException("获取锁失败！");
            }
        } catch (Throwable e) {
            throw e;
        } finally {
            redissionRepositoryCustom.unlock(lockName);
        }
    }
}
