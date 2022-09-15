package com.example.algorithm.sort;

import java.util.Arrays;
import java.util.Date;

/**
 * @author hpl
 * @date 2022/9/14 19:43
 */
public class SimpleSort {

    public static void main(String[] args) {
        //创建随机数组
        int[] testArr = new int[100000];

        for (int i = 0; i < testArr.length; i++) {
            //生成[0,8000]间的随机数
            testArr[i] = (int) (Math.random()*8000000);
        }

        //开始时间
        Date dataBegin = new Date();

        //冒泡排序
        bubbleSort(testArr);

        //结束时间
        Date dataEnd = new Date();
        System.out.println(dataEnd.getTime()-dataBegin.getTime());



    }
    /**
     * 冒泡排序
     * @param arr 进行排序的数组
     * @return 排好序的数组
     */
    public static int[] bubbleSort(int[] arr) {
        int temp = 0;
        int count = 0;

        for (int i =  arr.length-1; i > 0; i--) {
            count = 0;
            //每次循环都遍历出了一个最大的放在数组的后面
            for (int j = 0; j < i ; j++) {
                //如果比后一个元素大则进行交换
                if(arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    //记录进行交换的次数
                    count++;
                }
            }

//            System.out.println("第" + (arr.length-i)  +"次排序:");
//            System.out.println(Arrays.toString(arr));
            //说明已经有序了 无须在排
            if (count == 0) {
                return arr;
            }

        }
        return arr;
    }

    /**
     * 选择排序
     * @param arr 进行排序的数组
     * @return 排好序的数组
     */
    public static int[] selectSort(int[] arr) {
        int temp = 0;
        //最小值的下标
        int min = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            //先假设最小的数是arr[i]
            min = i;
            //遍历找到最小的那个
            for (int j = i + 1; j < arr.length; j++) {
                //如果发现arr[j] 比 arr[min]小 则令min = j
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            //将的到的最小值与arr[i]进行交换
            if (min != i) {
                temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
//            System.out.println("第" + (i+1)  +"次排序:");
//            System.out.println(Arrays.toString(arr));
        }
        return arr;
    }

    /**
     * 插入排序
     * @param arr 进行排序的数组
     * @return 排好序的数组
     */
    public static int[] insertSort(int[] arr) {
        //要插入的值
        int insertValue;
        //插入的下标
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            //先从已经排好序的数组中最后一个数开始比较
            insertIndex = i - 1;
            //如果下标小于0了说明已经跟 已排好序的数组全部做过了比较
            //如果要插入的那个值 小于 已排好序的数组中最后一个 九将这个数后移以为 然后在往前遍历 依此比较
            //知道找到一个比要插入值小的 则该点就是要插入的点
            //如果一次while循环也没进入 则说明要插入的这个值比 已排好序的数组中所有的都要大
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //将值插入
            arr[insertIndex + 1] = insertValue;

            System.out.println("第" + i +"次遍历排序:");
            System.out.println(Arrays.toString(arr));
        }

        return arr;
    }

    /**
     * 希尔排序
     * @param arr 进行排序的数组
     * @return 排好序的数组
     */
    public static int[] shellSort(int[] arr) {

        int temp = 0;
        //分成几个一组
        int group = arr.length / 2;
        while (group != 0) {
            for (int i = group; i < arr.length; i++) {
                //遍历每组中的元素，步长为group
                for (int j = i - group; j >= 0; j -= group) {
                    //采用交换法
                    if (arr[j] > arr[j + group]) {
                        temp = arr[j];
                        arr[j] = arr[j + group];
                        arr[j + group] = temp;
                    }
                }
            }
//            System.out.println("第" + (++count)  +"次遍历排序:分成" + group +"组");
//            System.out.println(Arrays.toString(arr));
            //进行下一次分组
            group = group / 2;

        }
        return arr;
    }


    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    public static void quickSort(int[] arr, int star, int end) {
        if (star >= end) {
            return ;
        }
        int l = star;
        int r = end;
        int piovt = arr[(l + r)/2];
        while (l <= r) {
            while (l <= r && arr[l] < piovt) {
                l++;
            }
            while (l <= r && arr[r] > piovt) {
                r--;
            }
            //交换
            if (l <= r) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                l++;
                r--;
            }
        }
        quickSort(arr,star,r);
        quickSort(arr,l,end);

    }



}
