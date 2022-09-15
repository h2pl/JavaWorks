package com.javaworks.multithread;

/**
 * @author hpl
 * @date 2022/9/15 15:46
 */
public class test {
   static volatile int state = 1;

    public static void main(String[] args) {
        Thread t1;
        Thread t2;

        int []arr = {1,2,3,4,5,6};

        t1 = new Thread(()->{
            for (int i : arr) {
                while (state != 1) {}
                System.out.println(i);

                state = 2;
            }
        });

        t2 = new Thread(()->{
            for (int i : arr) {
                while (state == 1) {}
                System.out.println(i);
                state =1;
            }
        });

        t1.start();
        t2.start();
    }
}
