package com.javaworks.gc;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class gc {

    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.aa);
        a = null;
        System.out.println(a);
        System.gc();
        while (true) {

        }
    }

    private void gc() {
    }

    static class A extends Object {
        private String aa = "aa";

        @Override
        protected void finalize() throws Throwable {
            System.out.println("gc ok");
        }
    }

}
