package com.fzn.day09;

/**
 * @program: DataStructure
 * 描述：  插值查找算法
 * @author: fzn
 * @create: 2022-01-30 19:48
 **/
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100 ; i++) {
            arr[i] = i + 1;
        }
        // int[] arr = {1,8,10,89,1000,1234};
        // System.out.println(Arrays.toString(arr));
        int i = insertValueSearch(arr, 0, arr.length - 1, 10);
        System.out.println(i);
    }

    // 编写插值查找算法

    /**
     *
     * @param arr 传入的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        // 过滤掉不正确的
        if (left > right || findVal < arr[0] || findVal > arr[arr.length -1]) {
            return -1;
        }
        // 求mid
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            // 向右递归
            return insertValueSearch(arr, mid +1 ,right,findVal);
        }else if (findVal < midVal) {
            // 向左递归
            return insertValueSearch(arr, left , mid -1,findVal);
        }else {
            // 相等
            return mid;
        }
    }
}