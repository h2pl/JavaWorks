package com.javaworks.apt;


import com.javaworks.apt.dependency.Example;
import com.javaworks.constants.CommonConstants;

public class main {
    public static void main(String[] args) {
        ExampleFacade exampleFacade = InstanceUtil.getInstanceByName(ExampleFacadeImpl.class.getName(), CommonConstants.GET_INSTANCE_METHOD_NAME);
        exampleFacade.insert(Example.builder().id(1).desc("desc").name("name").build());
    }
}
