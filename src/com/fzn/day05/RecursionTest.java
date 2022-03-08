package com.fzn.day05;

/**
 * @program: DataStructure
 * 描述：      递归问题
 * @author: fzn
 * @create: 2022-01-22 18:38
 **/
public class RecursionTest {
    public static void main(String[] args) {
        // test(5);
        int factorial = factorial(3);
        System.out.println(factorial);
    }

    // 打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n -1 );
        }
        System.out.println("n=" + n);
    }

    // 阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }else {
            return factorial(n -1) * n;
        }
    }
}