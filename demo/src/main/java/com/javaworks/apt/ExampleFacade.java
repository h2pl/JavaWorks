package com.javaworks.apt;


import com.javaworks.apt.dependency.Example;

/**
 * @Author: hpl
 * @Date: 2021/11/19 14:16
 */
public interface ExampleFacade {

    void insert(Example example);

    void delete(Example example);

    void update(Example example);

    void query(int id);

    static ExampleFacadeImpl getInstance() {
        throw new UnsupportedOperationException();
    }

}
