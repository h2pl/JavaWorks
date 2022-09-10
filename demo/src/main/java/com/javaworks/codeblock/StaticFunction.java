package com.javaworks.codeblock;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2021/12/9
 */
public class StaticFunction {
    private static final int var;
    private static final int copy = copy();

    static {
        var = 1;
        System.out.println("static block");
    }

    public static int copy() {
        return var;
    }

    public static void func() {
        System.out.println(var);
        System.out.println(copy);
        System.out.println("static function");
    }

}
