package com.fzn.day07;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：      优化版的选择排序
 * @author: fzn
 * @create: 2022-01-28 14:52
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        System.out.println("排序之前："+Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序之后："+Arrays.toString(arr));
    }
    // 选择排序
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // 假设最小的数是数组的第一个
            int min = arr[i];
            for (int j =  i+ 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min  = arr[j]; // 重置min
                    minIndex = j;  // 重置minIndex
                }
            }
            // 将最小值 放在arr[0] 即交换
            if (minIndex != i ){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            // System.out.println("第"+(i+1)+"轮之后："+Arrays.toString(arr));
        }
    }
}