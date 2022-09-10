package com.javaworks.designpattern;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class DelegateTest {

    public static void main(String[] args) {
        Doit action = new Action();
        Doit log = new Log(action);
        log.doit();
    }
}

interface Doit {
    void doit();
}

class Action implements Doit {
    @Override
    public void doit() {
        System.out.println("action");
    }
}

class Log implements Doit {
    private Doit action;

    Log(Doit action) {
        this.action = action;
    }

    @Override
    public void doit() {
        System.out.println("logbefore");
        action.doit();
        System.out.println("logafter");
    }
}