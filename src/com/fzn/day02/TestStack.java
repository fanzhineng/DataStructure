package com.fzn.day02;

import java.util.Stack;

/**
 * @program: DataStructure
 * 描述：  栈  先入后出
 * @author: fzn
 * @create: 2022-01-16 22:36
 **/
public class TestStack {
    public static void main(String[] args) {
        Stack<Object> stack = new Stack<>();
        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        while (stack.size() > 0) {
            // 出栈
            System.out.println(stack.pop());
        }
    }
}