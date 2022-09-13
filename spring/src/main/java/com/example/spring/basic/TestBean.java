package com.example.spring.basic;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author hpl
 * @date 2022/9/13 8:30
 */
@Component
public class TestBean implements InitializingBean {

    @Resource
    UserService userService;

    @PostConstruct
    public void post() {
        System.out.println("post");
    }

    public void test(){
        System.out.println("test");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }
}
