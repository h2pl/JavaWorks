package com.javaworks.processor;

import java.lang.annotation.*;

/**
 * @Author: hpl
 * @Date: 2021/11/19 14:16
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE) // 用于修饰类
public @interface GenerateInstance {
}
