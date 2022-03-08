package com.fzn.day04;

import java.util.Scanner;

/**
 * @program: DataStructure
 * 描述：      数组模拟栈
 * @author: fzn
 * @create: 2022-01-18 13:37
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:表示退出程序");
            System.out.println("push:表示添加数据到栈(入栈)");
            System.out.println("pop:表示从栈取出数据(出栈)");
            System.out.print("请输入你的选择：");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.printf("出栈的数据是%d\n",pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
// 定义一个类 表示栈
class ArrayStack {
    // 栈的大小
    private int maxSize;
    // 数组模拟栈 放该数组中
    private int[] stack;
    // top 表示栈顶 初始化为-1
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }
    // 入栈 push
    public void push(int value) {
        // 先判断是否栈满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]  = value;
    }
    // 出栈 pop
    public int pop() {
        // 判断栈是否空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空 没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    // 遍历栈 遍历时需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()){
            System.out.println("栈空 没有数据");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}