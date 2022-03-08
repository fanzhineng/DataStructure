package com.fzn.day07;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：  冒泡排序
 * @author: fzn
 * @create: 2022-01-28 13:55
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};
        System.out.println("排序之前的数组："+Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序之后的数组："+Arrays.toString(arr));

    }

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        // 临时变量
        int temp = 0;
        // 标识变量 表示是否进行交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            // System.out.println("第"+(i+1)+"之后的数组："+Arrays.toString(arr));
            if (!flag) {
                // 在一趟排序中 一次交换都没有发生过
                break;
            }else {
                // 重置 进行下一次的判断
                flag = false;
            }
        }
    }
}