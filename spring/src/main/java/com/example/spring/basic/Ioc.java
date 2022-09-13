package com.example.spring.basic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hpl
 * @date 2022/9/13 8:29
 */
public class Ioc {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        TestBean testBean = (TestBean) applicationContext.getBean("testBean");

        testBean.test();

        //单例
        TestBean testBean2 = (TestBean) applicationContext.getBean("testBean");
        System.out.println(testBean == testBean2);


        //init
        TestBean singleton = new TestBean();
        for (Field field : singleton.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Resource.class)) {
                try {
                    field.set(singleton, null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for (Method method : singleton.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.invoke(singleton, null);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
