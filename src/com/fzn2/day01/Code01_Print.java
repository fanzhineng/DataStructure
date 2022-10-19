package com.fzn2.day01;

/**
 * @program: DataStructure
 * 描述：   打印一个数的32位的二进制
 *      & 运算    1&1 ==1
 *                0&0 ==0
 *                1&0 ==0
 * @author: fzn
 * @create: 2022-10-19 09:30
 **/
public class Code01_Print {
    public static void main(String[] args) {
        // int是32位表示
        int a = 11;
        print(a);

        // 利用 包装类变二进制
        System.out.println(Integer.toBinaryString(a));

        // 右移
        int b = -1024;
        print(b);
        print(b >> 1);
        // 不带符号右移
        print(b >>> 1);

        // 一个数的相反数 系统最小除外 是他自己
        int c = 5;
        int d = -5;
        d = (~c) +1;
        System.out.println(d);
    }

    private static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

}