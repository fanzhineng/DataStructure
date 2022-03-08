package com.fzn.day12;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：  堆排序
 * @author: fzn
 * @create: 2022-02-09 18:29
 **/
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4,6,8,5,9};
        heapSort(arr);
    }

    // 编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        // 分步完成
        // adjustHeap(arr,1,arr.length);
        // System.out.println(Arrays.toString(arr));
        // adjustHeap(arr,0,arr.length);
        // System.out.println(Arrays.toString(arr));

        int temp = 0;
        // 变成大顶堆
        for(int i = arr.length / 2 -1; i >=0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 交换
        for(int j = arr.length-1;j >0; j--) {
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    // 将一个数组（二叉树） 调整成一个大顶堆
    /**
     * 功能： 完成将以 i 对应的非叶子节点的数调整成大顶堆
     * @param arr 原数组待调整的数组
     * @param i 非叶子节点的数组中索引1
     * @param length 表示对多少个元素进行调整 length是在逐渐减少
     */
    public static void adjustHeap(int[] arr,int i, int length) {
        int temp = arr[i]; // 先取出当前元素的值 保存在临时变量
        for (int k = i * 2 +1 ; k < length; k = k * 2 +1) {
            if (k+1 < length && arr[k] < arr[k +1]) {
                // 说明左子节点的值小于右子节点的值
                k++; // k指向右子节点
            }
            if (arr[k] > temp) {
                // 如果子节点大于父节点
                arr[i] = arr[k]; // 把较大的值赋给当前节点
                i = k; // i指向k 继续循环比较
            }else {
                break;
            }
        }
        // 最大的已经在顶上了
        // 将temp值放入调整后的位置
        arr[i] = temp;
    }
}