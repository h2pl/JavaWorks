package com.javaworks.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hpl
 * @date 2022/9/14 7:24
 */
public class BloomTest {
    public static void main(String[] args) {
        int size = 1_000_000;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size,0.0003);
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        for (int i = 0; i < size; i++) {
            if (!bloomFilter.mightContain(i)) {
                System.out.println("有坏人逃脱了");
            }
        }
        List<Integer> list = new ArrayList<>(1000);
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("有误伤的数量：" + list.size());
    }
}
