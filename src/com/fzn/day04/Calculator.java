package com.fzn.day04;

/**
 * @program: DataStructure
 * 描述：      实现简单计算器
 * @author: fzn
 * @create: 2022-01-18 14:17
 **/
public class Calculator {
    public static void main(String[] args) {
        // 如何处理多位数
        String expression = "30+2*6-2";
        // 创建两个栈 一个数栈 一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        // 每次扫描的char保存到ch
        char ch = ' ';
        // 用于拼接多位数
        String keepNum = "";
        while (true) {
            // 依次得到每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            // 判断是什么做相当于的处理
            if (operStack.isOper(ch)) {
                // 如果是运算符 判断当前的符号栈是否为空
                if(!operStack.isEmpty()) {
                    // 如果符号栈有操作符，就进行比较如果当前的操作符的优先级小于等于栈中的操作符
                    // 在符号栈中pop出一个符号进行运算 将得到结果 入数栈 然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 把运算结果入数栈
                        numStack.push(res);
                    }
                    // 把当前操作符入符号栈
                    // 如果当前的操作符大于栈中的操作符 就直接入符号栈
                    operStack.push(ch);
                }else {
                    // 如果为空直接入栈
                    operStack.push(ch);
                }
            }else {
                // 如果是数直接入数栈 '1'
                // 当处理多位数时 不能发现一个数的时候就里面入栈他可能是多位数
                // 在处理数时需要想expression的表达式的index后再看一位 如果是数继续扫描 如果是符号才入栈
                // 因此我需要定义一个字符串变量进行拼接 处理多位数
                keepNum += ch;

                // 如果ch已经是最后一位 就直接入栈
                if (index == expression.length() -1 ) {
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    // 判断下一个字符是不是数字 如果是数字 就继续扫描 如果是运算符 则入栈
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        // 是操作符
                        numStack.push(Integer.parseInt(keepNum));
                        // 清空字符串 保证下次为空
                        keepNum = "";
                    }
                }
            }
            // 让index +1 并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        // 扫描完毕 顺序出栈
        while (true) {
            // 如果符号栈为空 则计算到最后的结果 数栈只有一个数字【结果】
            if (operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            // 入栈
            numStack.push(res);
        }
        // 将数栈的最后数 pop出 就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式 %s = %d",expression,res2);

    }
}

// 定义一个类 表示栈
class ArrayStack2 {
    // 栈的大小
    private int maxSize;
    // 数组模拟栈 放该数组中
    private int[] stack;
    // top 表示栈顶 初始化为-1
    private int top = -1;

    public ArrayStack2(int maxSize) {
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

    // 返回运算符的优先级 优先级使用数字表示 数字越大 则优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            // 其他的符号 目前只有四种
            return -1;
        }
    }
    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }
    // 计算方法
    public int cal(int num1, int num2, int oper) {
        // 用于存放计算结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:break;
        }
        return res;
    }

    // 增加一个方法 可以返回当前栈顶的值 但不是真的出栈 pop
    public int peek() {
        return stack[top];
    }
}