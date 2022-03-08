package com.fzn.day01;

import java.util.Scanner;

/**
 * @program: DataStructure
 * 描述：
 * @author: fzn
 * @create: 2022-01-12 22:05
 **/
public class CireArrayQueueDemo {
    public static void main(String[] args) {
        CireArrayQueue queue = new CireArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    //查看队列头的信息
                    try {
                        int res = queue.headQueue();
                        System.out.printf("取出队列头的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop =false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class CireArrayQueue {
    // 最大容量
    private int maxSize;
    // 队列头
    private int front;
    // 队列尾
    private int rear;
    // 该数据用于存放数据 模拟队列
    private int[] arr;

    public CireArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new  int[maxSize];
    }
    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }
    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }
    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满,不能加入数据~");
            return;
        }
        // 直接将数据存入
        arr[rear] = n;
        // 将rear 后移
        rear = (rear +1 ) % maxSize;
    }

    // 获取队列的数据 出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常来处理
            throw new RuntimeException("队列为空，不能去数据");
        }
        int value = arr[front];
        front = (front +1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        // 从front遍历 遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前数据有效数据个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    // 显示头数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}