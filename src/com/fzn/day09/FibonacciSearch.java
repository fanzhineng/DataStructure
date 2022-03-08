package com.fzn.day09;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述： 斐波拉契查找算法
 * @author: fzn
 * @create: 2022-01-30 20:50
 **/
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(arr, 1234);
        System.out.println(i);

    }

    // 因为我们后面的 mid = low + F(k - 1) - 1 需要使用斐波拉契数列 因此我们需要先获取一个斐波拉契数列
    // 非递归的方式得到
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    // 斐波拉契查找算法
    // 使用非递归的方式编写算法
    /**
     * @param arr 原数组
     * @param key 要查找的值
     * @return 返回对应的下标 如果没有-1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; // 表示斐波拉契分割数值对应的下标
        int mid = 0; // 存放mid值
        int[] f = fib(); // 获取到斐波拉契数列
        // 获取到斐波拉契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k] 值 可能大于arr 的长度 因此我们需要使用Arrays类 构造一个新的数组 并指向arr[]
        // 不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 实际上需要使用arr数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        // 使用while 循环处理 找到我们的数 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                // 我们应该继续想数组的前面查找 左边
                high = mid - 1;
                // 为什么是k--
                // 全部的元素 = 前面的元素 + 后边的元素
                // f[k] = f[k-1] + f[k-2]
                // 因为前面的有 f[k-1]个元素 所以可以进行继续拆分 f[k-1] = f[k-2] + f[k-3]
                // 即 在f[k-1]的前面继续查找 k--
                // 即下次循环 mid = f[k-1-1]-1
                k--;
            }else if(key > temp[mid]) { // 右边查找
                low = mid + 1;
                // 全部的元素 = 前面的元素 + 后边的元素
                // f[k] = f[k-1] + f[k-2]
                // 因为后面我们有f[k-2]个元素 所以可以进行继续拆分 f[k-1] = f[k-3] + f[k-4]
                // 即在f[k-2]的前面进行查找 k -=2;
                // 即下次循环 mid = f[k-1-2] - 1
                k -=2;
            }else {
                // 找到 需要确定返回的时候那个下标
                if (mid < high) {
                    return mid;
                }else {
                    return high;
                }
            }
        }
        // 没找到
        return -1;
    }
}