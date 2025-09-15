package com.investment.assets.controller;

import com.investment.assets.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("/test")
    public void test(){
        log.debug("/test");
        testService.test();
    }

}
