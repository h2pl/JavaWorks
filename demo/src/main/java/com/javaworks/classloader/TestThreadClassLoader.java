package com.javaworks.classloader;

import com.javaworks.classloader.test.TestClass123;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2021/12/8
 */
public class TestThreadClassLoader {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class<?> clazz = classLoader.loadClass(TestClass.class.getName());
            System.out.println("load TestClass.class successful");
            System.out.println("clazz ClassLoader =" + clazz.getClassLoader());

            ClassLoader classLoader1 = new UserClassLoader();

            Class<?> clazz1 = classLoader1.loadClass(TestClass123.class.getName());
            System.out.println("load com.javaworks.classloader.test.TestClass.class successful");
            System.out.println("clazz1 ClassLoader =" + clazz1.getClassLoader());
            System.out.println("clazz1 == clazz:" + (clazz1 == clazz));
            System.out.println("clazz1 ClassLoader == clazz ClassLoader:" + (clazz1.getClassLoader() == clazz.getClassLoader()));

            System.out.println(clazz1.hashCode());
            classLoader1 = null;
            clazz1 = null;

            ClassLoader classLoader2 = new UserClassLoader();

            Class<?> clazz2 = classLoader2.loadClass(TestClass123.class.getName());
            System.out.println("load com.javaworks.classloader.test.TestClass.class successful");
            System.out.println(clazz2.hashCode());
            System.out.println("clazz2 ClassLoader =" + clazz2.getClassLoader());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
