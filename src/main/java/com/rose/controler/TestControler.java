package com.rose.controler;

import com.rose.aspect.annotation.Lock;
import com.rose.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@Slf4j
@RestController
public class TestControler {

    @Inject
    private TestService testService;

    @Lock(value = "lock1")
    @GetMapping("/testControler")
    public void testControler() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("testControler");
    }

    @GetMapping("/testService")
    public void testService() throws InterruptedException {
        testService.testA();
    }
}