package com.javaworks.apt.dependency;


import lombok.Builder;
import lombok.ToString;

/**
 * @Author: hpl
 * @Date: 2021/11/19 15:16
 */

@Builder
@ToString
public class Example {
    int id;
    String name;
    String desc;

}
