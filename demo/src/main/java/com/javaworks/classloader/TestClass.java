package com.javaworks.classloader;

import com.javaworks.classloader.test.TestClass123;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2021/12/8
 */
public class TestClass {

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader1 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return classLoader.loadClass(TestClass123.class.getName());
            }
        };
        try {
            classLoader1.loadClass("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(classLoader1);
        System.out.println(classLoader1.getClass());

    }

}
