package com.javaworks.apt;


import com.javaworks.apt.dependency.Example;
import com.javaworks.apt.dependency.ExampleService;
import com.javaworks.apt.dependency.ExampleServiceImpl;
import com.javaworks.processor.GenerateInstance;

/**
 * @Author: hpl
 * @Date: 2021/11/19 14:16
 */
@GenerateInstance
public class ExampleFacadeImpl implements ExampleFacade {
    private final ExampleService exampleService = InstanceUtil.getExampleServiceInstance(ExampleServiceImpl.class.getName());

    @Override
    public void insert(Example example) {
        exampleService.insert(example);
    }

    @Override
    public void delete(Example example) {
        exampleService.delete(example);
    }

    @Override
    public void update(Example example) {
        exampleService.update(example);
    }

    @Override
    public void query(int id) {
        exampleService.query(id);
    }
}
