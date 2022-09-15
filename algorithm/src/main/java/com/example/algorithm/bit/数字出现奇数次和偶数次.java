package com.example.algorithm.bit;

/**
 * @author hpl
 * @date 2022/9/14 17:13
 */
public class 数字出现奇数次和偶数次 {
    public static void main(String[] args) {
        int []arr = {1,1,2,2,3,3,4,4,5,6,6};
        System.out.println(oddNum(arr));

        int []brr = {1,1,2,2,3,3,4,4,5,6};
        twoOddNum(brr);
    }

    public static int oddNum(int []arr){
        int res = 0;
        for (int i : arr) {
            res = res ^ i;
        }
        return res;
    }

    public static void twoOddNum(int []arr){
        //设置两个数为a和b
        int res = 0;
        for (int i : arr) {
            res = res ^ i;
        }

        //提取最右侧的1
        int rightOne = res & (~res + 1);

        //由于a^b！=0，所以一定有一位不等于0
        //假设第n位位1，那么a和b这一位一定一个是0一个是1,
        //偶数个的数也可以分为第n位为1和为0的两种，进行异或可以得到a，再用res和a异或得到b就行了。

        int onlyOne = 0;
        for (int i : arr) {
            if ((i & rightOne) == 1) {
                onlyOne ^= i;
            }
        }

        System.out.println("a=" + onlyOne);
        System.out.println("b=" + (onlyOne ^ res));
    }
}
