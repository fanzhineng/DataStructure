package com.fzn.day18Algorithm;

/**
 * @program: DataStructure
 * 描述：  二分查找算法非递归实现
 * @author: fzn
 * @create: 2022-02-16 15:23
 **/
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,67,100};
        int i = binarySearch(arr, 12);
        System.out.println("index="+ i);
    }
    // 二分查找的非递归实现
    /**
     *
     * @param arr   待查找的数组 是升序的数组
     * @param target 需要查找的数
     * @return 返回对应下标 -1 表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            }else if (arr[mid] > target) {
                right = mid -1; // 需要向左边查找
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}