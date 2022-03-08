package com.fzn.day09;


import java.util.ArrayList;

/**
 * @program: DataStructure
 * 描述：  使用二分查找的前提是该数组是有序的
 * @author: fzn
 * @create: 2022-01-30 19:16
 **/
public class BinarySearchPro {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        ArrayList<Integer> integers = binarySearch(arr, 0, arr.length, 1001);
        System.out.println(integers);
    }

    // 二分查找算法 有重复的也能全部返回

    /**
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标 如果没有找到就返回-1
     */
    public static ArrayList<Integer> binarySearch(int[] arr, int left, int right, int findVal) {
        // 当left > right 时 说明递归完毕 但是没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        // 中间
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            // 相等
            ArrayList<Integer> resIndexList = new ArrayList<>();

            // 扫描左边的
            int temp = mid -1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                // 放入集合中
                resIndexList.add(temp);
                temp -= 1; // 左移
            }
            resIndexList.add(mid);

            // 扫描右边的
            temp  = mid + 1;
            while (true) {
                if (temp > arr.length -1 || arr[temp] != findVal) {
                    break;
                }
                // 放入集合中
                resIndexList.add(temp);
                temp += 1; // 右移
            }

            return resIndexList;
        }
    }
}