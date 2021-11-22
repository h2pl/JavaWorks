package com.javaworks.apt.dependency;

/**
 * @Author: hpl
 * @Date: 2021/11/19 15:15
 */
public interface ExampleService {
    void insert(Example example);

    void delete(Example example);

    void update(Example example);

    void query(int id);

    public static ExampleService getInstance() {
        throw new UnsupportedOperationException();
    }

}
