package com.javaworks.designpattern;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class Singleton {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                One instance = getSingleton();
                System.out.println(instance);
            }).start();
        }
    }

    private static volatile One INSTANCE = null;

    public static One getSingleton() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new One();
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }

}


class One {

}
