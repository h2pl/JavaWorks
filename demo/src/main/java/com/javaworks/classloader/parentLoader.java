package com.javaworks.classloader;

import com.javaworks.classloader.test.TestClass123;

import java.util.ServiceLoader;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class parentLoader {
    public static void main(String[] args) {
        ServiceLoader.load(TestClass123.class);
        System.out.println(TestClass123.class.getClassLoader());
    }
}
