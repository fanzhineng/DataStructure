package com.fzn.day07;

/**
 * @program: DataStructure
 * 描述：  八皇后问题
 * @author: fzn
 * @create: 2022-01-27 19:04
 **/
public class Queue8 {
    // 定义一个max表示共有多少个皇后 比如 arr = {0,4,7,5,2,6,1,3}
    int max = 8;
    // 使用一维数组
    int[] array = new int[max];
    // 统计解法
    static int count = 0;

    public static void main(String[] args) {
        // 测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
    }

    // 编写一个方法 放置第n个皇后
    private void check(int n) {
        if (n == max) {
            // n== 8 其实8个皇后已经放好了
            print();
            return;
        }
        // 依次放入皇后并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后n 放到该行的第1列
            array[n] = i;
            // 判断当前放置第n个皇后到i列的时候是否冲突
            if (judge(n)) {
                // 不冲突 接着放n+1
                check(n + 1);
            }
            // 如果冲突 就继续执行 array[n] = i; 即将第n个皇后 放置在本行的后移一个位置
        }
    }

    // 查看当我们放置第n个皇后，就去检测皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n n表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 表示第n个皇后是否和前面的n-1个皇后在同一列
            // Math.abs(n-i) == Math.abs(array[n] -array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 写一个方法 将皇后摆放的位置打印输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}