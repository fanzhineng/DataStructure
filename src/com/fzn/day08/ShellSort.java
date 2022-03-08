package com.fzn.day08;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：  希尔排序
 * @author: fzn
 * @create: 2022-01-29 14:48
 **/
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序之前的数组：" + Arrays.toString(arr));
        // shellSort(arr);
        shellSort2(arr);
        System.out.println("排序之后的数组：" + Arrays.toString(arr));

    }

    // 希尔排序 交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        // 增量 并缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前的元素大于加上步长的那个元素 说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 希尔排序 移位法  优化版的
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 退出表示找到了插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}