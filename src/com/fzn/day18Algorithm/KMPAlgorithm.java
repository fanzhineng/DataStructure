package com.fzn.day18Algorithm;

import java.util.Arrays;

/**
 * @program: DataStructure
 * 描述：      KMP算法
 * @author: fzn
 * @create: 2022-02-21 11:07
 **/
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //匹配表
        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));

        // kmp匹配算法
        int index = kmpSearch(str1, str2, next);
        System.out.println(index);

    }
    // 写出kmp的搜索算法
    /**
     *
     * @param str1 源字符串
     * @param str2 需要匹配的字符串
     * @param next 部分匹配表
     * @return 如果返回-1就没有匹配上 否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历
        for (int i = 0,j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i-j+1;
            }
        }
        return -1;
    }

    // 获取一个字符串的部分匹配值
    public static int[] kmpNext(String dest){
        // 创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;  // 如果字符串的长度为1 部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length() ; i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j-1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}