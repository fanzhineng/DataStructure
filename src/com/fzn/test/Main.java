package com.fzn.test;

/**
 * @program: DataStructure
 * 描述：
 * @author: fzn
 * @create: 2021-12-04 20:50
 **/
public class Main {
    public static void main(String[] args) {
        // 方法一
        // System.out.println(fib(0));
        // System.out.println(fib(1));
        System.out.println(fib(60));
        // 方法二
        System.out.println(fib2(60));
    }

    /**
     * 斐波那契数列
     * 0    1   1   2   3   5   8   13
     * 方法一：利用递归的方式
     *  缺陷：
     *      性能问题  当大于46时算的比较慢 47还成了负数
     */
    public static int fib(int n){
        if (n <= 1){
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 方法二：利用循环和指针的概念去解决问题
     */
    public static int fib2(int n){
        if (n <= 1){
            return n;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < n-1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}