package com.fzn.day18Algorithm;

/**
 * @program: DataStructure
 * 描述： 分治算法--汉诺塔
 * @author: fzn
 * @create: 2022-02-16 15:44
 **/
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
    }
    //汉诺塔的移动方案 使用分治算法
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从"+ a + "->" + c);
        }else {
            // 总是看成两个盘 最下面的一个盘和上面的所有盘
            // 先把最上面的所有盘A-B 移动过程会使用到c
            hanoiTower(num -1,a, c, b);
            // 把最下面的盘 A-C
            System.out.println("第"+ num + "个盘从" + a + "->" +c);
            // 把B塔的所有盘移动到c 移动过程使用到a
            hanoiTower(num -1,b, a, c);
        }
    }
}