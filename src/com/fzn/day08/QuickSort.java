package com.fzn.day08;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：  快速排序
 * @author: fzn
 * @create: 2022-01-29 15:56
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 701,-1,900,456};

        quickSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left; // 左下标
        int r = right; // 右下标
        // 中轴
        int pivot = arr[(left + right) / 2];
        // 临时变量
        int temp = 0;
        // 循环的目的是让比pivot 值小的放到左边 大的放到右边
        while (l < r) {
            // 在pivot的左边一直找 找到大于等于pivot的值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找 找到小于等于pivot的值才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 如果l 大于等于 r 说明pivot的左右两边的值
            // 已经按照左边全部是小于等于pivot的 右边全部是大于的等于pivot
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换之后发现 arr[l] = pivot值 相等 r--，前移一步
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换之后发现 arr[r] = pivot值 相等 l++，后移一步
            if (arr[r] == pivot) {
                l += 1;
            }
        }

        // 如果l == r 必须l++ r-- 否则出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }
}