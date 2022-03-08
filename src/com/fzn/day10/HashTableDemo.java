package com.fzn.day10;


import java.util.Scanner;

/**
 * @program: DataStructure
 * 描述：
 * @author: fzn
 * @create: 2022-02-01 11:13
 **/
public class HashTableDemo {
    public static void main(String[] args) {
        // 创建一个hash表
        HashTab hashTab = new HashTab(7);

        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add：  添加雇员");
            System.out.println("list： 显示雇员");
            System.out.println("find： 查找雇员");
            System.out.println("exit： 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建一个雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

}


// 创建HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size  = size;
        // 初始化
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加
    public void add(Emp emp) {
        // 根据员工的id 得到该员工应当加入到哪条链表中
        int empLinkedListNo = hashFun(emp.id);
        // 将emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    // 遍历所有的链表 hash
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i+1);
        }
    }

    // 根据输入的id 查找雇员
    public void findEmpById(int id) {
        // 使用散列函数确定到那条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            // 找到
            System.out.printf("在第%d条链表中找到该 雇员 id = %d\n",empLinkedListNo+1, id);
        }else {
            System.out.println("在hash表中没有找到该雇员");
        }
    }

    // 编写一个散列函数
    public int hashFun(int id) {
        return id % size;
    }
}


// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; // 默认为null
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
// 创建EmpLinkedList 表示链表
class EmpLinkedList {
    // 头指针 执行第一个Emp 因此我们这个链表的head 是直接指向第一个Emp
    private Emp head;

    // 添加雇员到链表
    // 1.假定 当添加雇员时 id是自增长 即id是分配总是从小到大的
    // 因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if(head == null) {
            head = emp;
            return;
        }
        // 如果不是添加第一个雇员 则使用一个辅助指针 帮组定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                // 说明到链表最后
                break;
            }
            curEmp = curEmp.next; // 后移
        }
        // 退出时直接将emp加入链表
        curEmp.next = emp;
    }

    // 遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            // 说明链表为空
            System.out.println("第"+no+"当前链表为空");
            return;
        }
        System.out.println("第"+no+"链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            if (curEmp.next == null) {
                // 已经是最后的节点
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    // 根据id 查找雇员
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                // 找到
                break;
            }
            // 退出
            if (curEmp.next == null) {
                // 说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}