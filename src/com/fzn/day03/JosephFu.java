package com.fzn.day03;

/**
 * @program: DataStructure
 * 描述：  约瑟夫环问题
 * @author: fzn
 * @create: 2022-01-17 17:04
 **/
public class JosephFu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();

        circleSingleLinkedList.addBoy(5);

        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1,2,5); // 2 4 1 5 3

    }
}
// 创建环形链表
class CircleSingleLinkedList {
    // 创建一个first节点 当前没有编号
    Boy first = new Boy(-1);
    // 添加小孩节点 构建成一个环形的链表
    public void addBoy(int nums) {
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first);// 构成环
                curBoy = first; // 让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                // 后移
                curBoy = boy;
            }
        }
    }

    // 遍历当前环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号 %d \n",curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            // 后移
            curBoy = curBoy.getNext();

        }
    }

    // 根据用户的输入 计算出圈的顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误 请重新输入");
            return;
        }
        // 创建要给的辅助变量 帮助小孩出圈
        Boy helper = first;
        // 先让这个变量指向最后的一个节点 first前一个
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 报数前 先让first和helper 移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 移动m -1 次 然后出圈 直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                // 说明圈中只有一个节点
                break;
            }
            // 让 first 和 helper 指针同时移动 countNum -1
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            // 这时first指向的小孩要出圈
            first = first.getNext();
            helper.setNext(first);
        }

        System.out.printf("最后留在圈中的小孩编号%d\n", helper.getNo());


    }
}

class Boy {
    // 编号
    private int no;
    // 指向下一个节点 默认为null
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public Boy(int no, Boy next) {
        this.no = no;
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}