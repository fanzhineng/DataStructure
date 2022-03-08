package com.fzn.day05;

import com.fzn.day04.PolandNotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: DataStructure
 * 描述：      中缀表达式转换为后缀表达式
 * @author: fzn
 * @create: 2022-01-22 16:34
 **/
public class PolandNotation2 {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List="+list);
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("后缀表达式对应的List="+list);
        // 计算结果
        int calculate = PolandNotation.calculate(list1);
        System.out.println(calculate);
    }

    // 中缀表达式转换为后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        // 定义两个栈
        // 符号栈
        Stack<String> s1 = new Stack<>();
        // 存储中间结果的栈
        // 因为s2在整个操作中没有pop操作 而且后面我们还需要逆序输出 因此比较麻烦这里就不用 而是使用list
        // Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        // 遍历ls
        for (String item : ls) {
            // 如果是一个数就入栈 s2
            if (item.matches("\\d+")) {
                s2.add(item);
            }else if (item.equals("(")) {
                s1.push(item);
            }else if(item.equals(")")) {
                // 如果是右括号则依次弹出s1栈顶的运算符并压入s2中 直到遇到左括号为止 此时将这一对括号不要
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                // 将（弹出 消除（）
                s1.pop();
            }else {
                // 优先级问题
                // 当item的优先级小于等于s1的栈顶的优先级
                // 将s1的栈顶的运算符弹出并压入到s2中 再次转到4.1与s1中新的栈顶运算符相比较
                // 问题：比较运算符优先级的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 将item 压入栈中
                s1.push(item);
            }
        }
        // 将s1剩余的运算符压入到我们的栈中
        while (s1.size() != 0 ) {
            s2.add(s1.pop());
        }
        // 然后正常输出即可
        return s2;
    }

    // 将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        // 定义一个list存放中缀表达式存放的对应的内容
        List<String> ls = new ArrayList<>();
        // 遍历指针
        int i = 0;
        // 多位数的拼接
        String str;
        char c;
        do {
            // 如果c是一个非数字 加入到ls 0-9
            if ((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57) {
                // 加入
                ls.add(""+ c);
                i++;
            }else {
                // 如果是一个数 考虑多维数
                str = "";
                while (i < s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());

        return ls;
    }
}

// 编写一个类 可以返回一个运算符 对应的优先级
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    // 写一个方法 返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result += ADD;
                break;
            case "-":
                result += SUB;
                break;
            case "*":
                result += MUL;
                break;
            case "/":
                result += DIV;
                break;
            default:
                // System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}