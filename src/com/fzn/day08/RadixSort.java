package com.fzn.day08;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：  基数排序
 * @author: fzn
 * @create: 2022-01-29 19:16
 **/
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        System.out.println("排序之前的数组："+Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序之后的数组："+Arrays.toString(arr));
    }

    // 基数排序  是使用空间换时间的经典算法
    public static void radixSort(int[] arr) {

        // 得到数组中最大的数 假定第一个为最大
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大是几位数
        int maxLength = (max + "").length();

        // 定义一个二维数组表示十个桶 每一个桶就是一个一维数组
        // 为了防止数据溢出每个一个数组的大小为原数组的大小
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中实际存放了多少个数据 我们定义一个一维数组来记录各个桶的每次放入的数据个数
        // bucketElementCounts[0] 记录的是 bucket[0]的个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的个位 十 百 ...  对应的位数
                int digitOfElement = arr[j] / n % 10;
                // 放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                // 个数++
                bucketElementCounts[digitOfElement]++;
            }

            // 按照桶的顺序放入原数组中
            int index = 0;
            // 遍历每一个桶 并将桶中的数据放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据才放入到原数组中 如果没有就不需要放入
                if (bucketElementCounts[k] != 0) {
                    // 循环该桶即第k个桶 放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出元素放入arr中
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                // 需要将每个bucketElementCounts[k] 变为0 方便我们下次使用
                bucketElementCounts[k] = 0;
            }
            // System.out.println("第"+(i+1)+"轮排序处理" + Arrays.toString(arr));
        }



    }
}