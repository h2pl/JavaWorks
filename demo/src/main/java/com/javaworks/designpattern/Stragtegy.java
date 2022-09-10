package com.javaworks.designpattern;

/**
 * Created by Intellij IDEA.
 * User:  penglong.huang
 * Date:  2022/9/10
 */
public class Stragtegy {
    public static void main(String[] args) {
        RateLimit tokenBucket = new TokenBucket();
        execute(tokenBucket);

        RateLimit bucket = new Bucket();
        execute(bucket);
    }

    static void execute(RateLimit rateLimit) {
        rateLimit.run();
    }
}


class TokenBucket implements RateLimit {

    @Override
    public void run() {
        System.out.println("TokenBucket");
    }
}

class Bucket implements RateLimit {

    @Override
    public void run() {
        System.out.println("Bucket");
    }
}

interface RateLimit {
    void run();
}
