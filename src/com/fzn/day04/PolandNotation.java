package com.fzn.day04;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: DataStructure
 * 描述：  逆波兰表达式实现计算器
 * @author: fzn
 * @create: 2022-01-18 18:01
 **/
public class PolandNotation {
    public static void main(String[] args) {
        // 逆波兰表达式  数字符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";
        // 思路如下
        // 1. 先放入ArrayList中
        // 2. 再将ArrayList 传递给一个方法 遍历ArrayList 配合栈 完成计算
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);

        int res = calculate(rpnList);
        System.out.println(res);
    }

    // 将逆波兰表达式 依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        // 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    // 完成对逆波兰表达式的运算
    /*
     *  1)从左至右扫描 将3和4压入堆栈
     *  2）遇到+运算符，因此弹出4和3（4为栈顶元素 3为次顶元素） 计算3+4的值 7 再将7入栈
     *  3）将5入栈
     *  4）接下来是x运算符 因此弹出5和7 计算5*7 = 35 将35入栈
     *  5）将6入栈
     *  6）最后是-运算符 计算35-6的值 即29 由此得到最终的结果
     */
    public static int calculate(List<String> ls) {
        // 创建一个栈
        Stack<String> stack = new Stack<>();
        // 遍历
        for (String item : ls) {
            // 正则表达式取出数
            if (item.matches("\\d+")) { //匹配多位数
                // 入栈
                stack.push(item);
            }else {
                // 从pop出两个数并运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")) {
                    res = num1 + num2;
                }else if(item.equals("-")) {
                    res = num1 - num2;
                }else if(item.equals("*")) {
                    res = num1 * num2;
                }else if(item.equals("/")) {
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                // 把res入栈
                stack.push(res + "");
            }
        }
        // 返回结果
        return Integer.parseInt(stack.pop());
    }
}
