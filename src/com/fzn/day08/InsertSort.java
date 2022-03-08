package com.fzn.day08;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：
 * @author: fzn
 * @create: 2022-01-29 14:25
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        System.out.println("排序之前的数组："+Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序之后的数组："+Arrays.toString(arr));
    }

    // 插入排序
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 定义带插入的数
            int insertVal = arr[i];
            // 要插入的下标
            int insertIndex = i - 1;
            // 保证数组不越界
            // 待插入的数还么有找到插入的位置 就需要将arr[insertVal] 后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出while循环的时候就说明插入的位置已经找到了 insertIndex+1
            arr[insertIndex + 1] = insertVal;
        }
    }
}