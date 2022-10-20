package com.fzn2.day01;

/**
 * @program: DataStructure
 * 描述：      Math.random()  随机返回 [0,1)的范围的小数
 * @author: fzn
 * @create: 2022-10-19 17:20
 **/
public class Code04_RandToRand {
    public static void main(String[] args) {
        // 等概率返回一个数
        int testTimes = 10000000;
        int count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.8){
                count++;
            }
        }
        System.out.println( (double) count / (double) testTimes);

        System.out.println("------------------");
        // [0,1) -> [0,8)
        count = 0;
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() * 8 < 5) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println((double) 5 / (double) 8);

        int K = 9;
        // [0,K) -> [0,8]

        int[] counts = new int[9];
        for (int i = 0; i < testTimes; i++) {
            int ans = (int) (Math.random() * K); // [0,K-1]
            counts[ans]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
        System.out.println("=========");

        count = 0;
        double x = 0.17;
        for (int i = 0; i < testTimes; i++) {
            if (xToXPower2() < x) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);
        System.out.println(Math.pow(x, 2));
    }

    // 返回[0,1)的一个小数
    // 任意的x，x属于[0,1)，[0,x)范围上的数出现概率由原来的x调整成x平方
    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }
}