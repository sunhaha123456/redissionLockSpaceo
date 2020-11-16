package com.rose.service.impl;

import com.rose.aspect.annotation.Lock;
import com.rose.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Lock(value = "lock2")
    @Override
    public void testA() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("testA");
    }
}