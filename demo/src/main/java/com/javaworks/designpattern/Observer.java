package com.javaworks.designpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class Observer {
    public static void main(String[] args) {
        Item item = new Item("a");
        item.addListener(new Listener("l1"));
        item.addListener(new Listener("l2"));
        item.execute();

        Item item1 = new Item("b");
        item1.execute();
    }
}

class Listener {
    Listener(String name) {
        this.name = name;
    }

    String name;

    void begin() {
        System.out.println(name + "begin");
    }

    void end() {
        System.out.println(name + "end");
    }
}

class Item {
    Item(String name) {
        this.name = name;
    }

    String name;
    List<Listener> listeners = new ArrayList<>();

    void addListener(Listener listener) {
        listeners.add(listener);
    }

    void execute() {
        notifyListener();
        System.out.println(name + "execute");
    }

    void notifyListener() {
        listeners.forEach(Listener::begin);
        listeners.forEach(Listener::end);
    }

}
