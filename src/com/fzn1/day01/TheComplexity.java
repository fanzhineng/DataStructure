package com.fzn1.day01;

/**
 * @program: DataStructure
 * 描述：  算法的复杂度
 * @author: fzn
 * @create: 2022-10-17 11:20
 **/
public class TheComplexity {

    public static void main(String[] args) {
        // 求斐波拉契数列
        // System.out.println(fib(19));
        // System.out.println(fib(64));

        // System.out.println(fib2(64));

        // 引入测试时间
        Times.test("fib1", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib(32));
            }
        });
        Times.test("fib2", new Times.Task() {
            @Override
            public void execute() {
                System.out.println(fib2(32));
            }
        });

        System.out.println(fib3(32));
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

    /**
     * 方法三
     * 利用公式求
     */
    public static  int fib3(int n) {
        double c = Math.sqrt(5);
        return (int) ((Math.pow((1+c)/2,n) - Math.pow((1-c)/2,n))/c);
    }
}