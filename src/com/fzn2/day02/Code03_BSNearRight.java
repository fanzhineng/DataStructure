package com.fzn2.day02;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：      有序数组中找小于等于 value中最右边的位置
 * @author: fzn
 * @create: 2022-10-20 09:50
 **/
public class Code03_BSNearRight {
    /**
     * 小于等于出现的最右边的位置
     * @param arr
     * @param value
     * @return
     */
    private static int nearestIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0){
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        // 出现的位置
        int ans = -1;
        while (L <= R){
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > value){
                R = mid - 1;
            }else {
                // <= 这个数
                ans = mid;
                L = mid + 1;
            }
        }
        return ans;
    }


    // for test
    public static int test(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != nearestIndex(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearestIndex(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}