package com.javaworks.apt;


import com.javaworks.apt.dependency.ExampleService;

import java.lang.reflect.Method;

/**
 * @Author: hpl
 * @Date: 2021/11/19 15:57
 */
public class InstanceUtil {

    public static ExampleService getExampleServiceInstance(String serviceImplName) {
        ExampleService instance = null;
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Class<?> clazz = Class.forName(serviceImplName, true, cl);
            Method instanceMethod = clazz.getMethod("getInstance");
            instance = (ExampleService) instanceMethod.invoke(clazz);
        } catch (Throwable t) {
            System.err.println("[getExampleServiceInstance] Handling class[" + serviceImplName + "] failed: " + t);
            System.exit(0);
        }
        return instance;
    }

    public static <T> T getInstanceByName(String className, String methodName) {
        T instance = null;
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            Class<?> clazz = Class.forName(className, true, cl);
            Method instanceMethod = clazz.getMethod(methodName);
            instance = (T) instanceMethod.invoke(clazz);
        } catch (Throwable t) {
            System.err.println("[getInstanceByName] Handling class[" + className + "] by method: " + methodName + "failed: " + t);
            System.exit(0);
        }
        return instance;
    }

}
