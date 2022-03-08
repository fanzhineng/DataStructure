package com.fzn.day08;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：  归并排序
 * @author: fzn
 * @create: 2022-01-29 16:28
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    // 分+和的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    // 合并的方法

    /**
     * @param arr   需要排序的原始数组
     * @param left  左边有序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i 左边有序序列的初始索引
        int j = mid + 1; // 初始化j  右边有序序列的初始索引
        int t = 0; // 临时数组的开始索引 指向temp的当前索引

        // 先把左右两边的数据按照规则填充到temp数组 直到左右两边的有序序列有一方完毕为止
        while (i <= mid && j <= right) {
            // 继续做
            // 如果左边的有序序列的当前元素 小于等于右边有序序列的当前元素
            // 把左边的当前元素拷贝到temp中  指针后移 t++ i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                // 右边的小于左边的
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        // 把有剩余数据的一边依次全部填充到temp中
        while (i <= mid) {
            // 左边的有序序列还有剩余的元素 就全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            // 右边的有序序列还有剩余的元素 就全部填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        // 将temp数组的元素拷贝到arr
        // 注意： 并不是每次都拷贝所有 最后一次才是
        t = 0;
        int tempLeft = left;
        // 合并
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }

}