package com.fzn2.day01;

/**
 * @program: DataStructure
 * 描述：  选择排序和冒泡排序
 * @author: fzn
 * @create: 2022-10-19 10:58
 **/
public class Code02_ArraySort {
    public static void main(String[] args) {
        int[] arr = {7,1,3,5,1,6,8,1,3,5,7,5,6};
        printArray(arr);

        // selectSort(arr);

        // bubbleSort(arr);

        // insertSort1(arr);
        insertSort2(arr);
        printArray(arr);


    }
    /**
     * 插入排序  优化版
     * @param arr 排序的数组
     */
    private static void insertSort2(int[] arr) {
        // 判断是否需要排序
        isReturn(arr);
        
        int N = arr.length;
        for(int end = 1; end < N;end++) {
            // pre 新数的前一个位置
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr,pre,pre + 1);
            }
        }
    }

    /**
     * 插入排序
     * @param arr 排序的数组
     */
    private static void insertSort1(int[] arr) {
        // 判断是否需要排序
        isReturn(arr);

        // 0-0
        // 0-1
        // 0-2
        // 0-n-1
        int N = arr.length;
        for(int end = 1; end < N;end++) {
            // 判断的数 是否比前一个大
            int newNumIndex = end;
            while (newNumIndex - 1 >= 0 && arr[newNumIndex -1] > arr[newNumIndex]){
                swap(arr,newNumIndex - 1, newNumIndex);
                newNumIndex--;
            }
        }
    }

    /**
     * 冒泡排序
     * @param arr 排序的数组
     */
    private static void bubbleSort(int[] arr) {
        // 判断是否需要排序
        isReturn(arr);

        int N = arr.length-1;
        // 1  n-1
        // 1  n-2
        // 1  n-3
        // 1 end
        for (int end = N;end >= 0; end--){
            // 干的事
            for(int second = 1; second <= end; second++){
                if (arr[second - 1] >arr[second]) {
                    swap(arr,second-1, second);
                }
            }
        }
    }

    /**
     * 选择排序
     * @param arr 排序的数组
     */
    private static void selectSort(int[] arr) {
        // 判断是否需要排序
        isReturn(arr);

        // 长度
        int N  = arr.length;
        for (int i = 0; i < N; i++) {
            // 最小值下标
            int minValueIndex = i;
            for (int j = i+1; j < N; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr,i,minValueIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void  isReturn(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
    }

}