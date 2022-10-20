package com.fzn2.day01;

/**
 * @program: DataStructure
 * 描述：      借助f 函数 得到1-7等概率的数
 * @author: fzn
 * @create: 2022-10-19 18:37
 **/
public class Code05_RandomExe {

    public static void main(String[] args) {
        // 等概率返回 0-1
        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (a() == 0) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println("==========");

        // 等概率返回 1-7
        int[] counts = new int[8];
        for (int i = 0; i < testTimes; i++) {
            int num = f2();
            counts[num]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
    }
    // 此函数只能用，不能修改
    // 等概率返回1~5  [1-5]
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    // 等概率得到0和1
    public static int a() {
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }
    // 等概率返回0~6
    public static int b() {
        int ans = 0;
        do {
            // 调用三次二进制位等概率得到 0-1
            ans = (a() << 2) + (a() << 1) + a();
        } while (ans == 7);
        return ans;
    }
    // 等概率返回1~7
    public static int f2() {
        return b() + 1;
    }

    // ---------------------------------

    // 你只能知道，x会以固定概率返回0和1，但是x的内容，你看不到！
    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }
    // 等概率返回0和1
    public static int y() {
        int ans = 0;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }

}