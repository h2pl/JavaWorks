package com.javaworks.lru;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("a", "a");
        lruCache.put("b", "b");
        lruCache.put("c", "c");
        lruCache.put("d", "d");
        lruCache.put("e", "e");
        lruCache.print();

        lruCache.put("f", "f");

        lruCache.print();

        System.out.println(lruCache.get("a"));
        System.out.println(lruCache.get("d"));
        System.out.println(lruCache.get("f"));

        lruCache.print();
    }

    void print() {
        nodes.forEach(
                node -> {
                    System.out.println(node.key);
                    System.out.println(node.value);
                }
        );
    }


    LinkedList<Node> nodes = new LinkedList<>();
    Map<String, Node> map = new HashMap<>();
    Integer size;

    LRUCache(Integer size) {
        this.size = size;
    }

    void put(String key, String val) {
        Node node = new Node(key, val);
        map.put(key, node);

        if (size == map.size()) {
            nodes.removeLast();
        }
        nodes.addFirst(node);
    }

    Node get(String key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node node = map.get(key);
        nodes.remove(node);
        nodes.addFirst(node);

        return node;
    }

}

@Data
class Node {
    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String key;
    String value;

    @Override
    public String toString() {
        return "Node{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
