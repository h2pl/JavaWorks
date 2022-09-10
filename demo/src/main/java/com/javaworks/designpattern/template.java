package com.javaworks.designpattern;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class template {

    public static void main(String[] args) {
        FunctionTemplate functionTemplate = new SimpleTemplate();
        functionTemplate.execute();
        
    }

}

abstract class FunctionTemplate {
    abstract void begin();

    void log() {
        System.out.println("log");
    }

    abstract void doit();

    abstract void end();

    void execute() {
        begin();
        log();
        doit();
        end();
    }

}

class SimpleTemplate extends FunctionTemplate {

    @Override
    void begin() {

    }

    @Override
    void doit() {

    }

    @Override
    void end() {

    }
}


