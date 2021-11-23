package com.javaworks.apt.dependency;


import com.alibaba.fastjson.JSON;
import com.javaworks.processor.GenerateInstance;

/**
 * @Author: hpl
 * @Date: 2021/11/19 15:15
 */
@GenerateInstance
public class ExampleServiceImpl implements ExampleService {

    @Override
    public void insert(Example example) {
        System.out.println("insert:" + example.toString());
    }

    @Override
    public void delete(Example example) {
        System.out.println("delete:" + JSON.toJSONString(example));

    }

    @Override
    public void update(Example example) {
        System.out.println("update:" + JSON.toJSONString(example));

    }

    @Override
    public void query(int id) {
        System.out.println("query:" + id);
    }

}
