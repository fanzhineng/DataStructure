package com.fzn.day02;


import java.util.Stack;

/**
 * @program: DataStructure
 * 描述：  定义单链表
 * @author: fzn
 * @create: 2022-01-14 13:55
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "黄建飞", "傻儿子");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 正常加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        // 加入按照编号的顺序
        // singleLinkedList.addByOrder(hero1);
        // singleLinkedList.addByOrder(hero4);
        // singleLinkedList.addByOrder(hero2);
        // singleLinkedList.addByOrder(hero3);
        // // singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();
        // System.out.println("=====修改之后的链表=====");
        // HeroNode newHeroNode = new HeroNode(2, "小黄", "傻逼");
        // singleLinkedList.update(newHeroNode);

    //     singleLinkedList.del(2);
    //
    //     // 长度
    //     System.out.println(SingleLinkedList.getLength(singleLinkedList.getHead()));
    //
    //     singleLinkedList.list();
    //     System.out.println("=============");
    //     HeroNode lastIndexNode = SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 2);
    //     System.out.println(lastIndexNode);

        System.out.println("反转之后 ");
        SingleLinkedList.reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("从尾到头打印单链表");
        SingleLinkedList.reversePrint(singleLinkedList.getHead());

    }
}

// 定义SingleLinkedList 单链表
class SingleLinkedList {
    // 先初始化一个头节点，头结点不要动
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单向链表
    // 1.找到当前链表的最后节点
    // 2.将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode) {
        // 因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表 找到最后
        while (true) {
            // temp.next = null
            if (temp.next == null){
                // 最后一个
                break;
            }
            // 如果没有找到最后 就将temp 后移
            temp= temp.next;
        }
        // 当退出while循环之后， temp就指向了链表的最后
        temp.next = heroNode;
    }

    // 显示链表[遍历]
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量
        HeroNode temp = head.next;
        while (true) {
            if (temp == null){
                // 最后一个
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 第二种方式在添加的时候 根据排名插入到指定位置
    public void addByOrder(HeroNode heroNode) {
         HeroNode temp  = head;
         // 标志添加的编号是否存在 默认为false
         boolean flag = false;
         while (true) {
             if (temp.next == null) {
                 // 说明temp已经在链表的最后
                 break;
             }
             if (temp.next.no > heroNode.no) {
                 // 位置找到，就在temp的后面的插入
                 break;
             }else if (temp.next.no == heroNode.no) {
                 // 编号已经存在
                 flag = true;
                 break;
             }
             temp = temp.next;
         }
         // 判断flag的值
        if (flag) {
            // 不能添加 说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了,不能加入\n",heroNode.no );
        }else {
            // 插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 修改节点的信息，根据no编号的修改，即no编号 不能修改
    // 根据newHereNode 的no 来修改即可
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 定义一个辅助变量
        HeroNode temp = head;
        // 表示是否找到改节点
        boolean flag = false;
        while (true) {
            if (temp == null){
                // 已经遍历完
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag 判断是否找到需要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else {
            // 没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n",newHeroNode.no);
        }
    }

    // 删除节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                // 找到的待删除节点的前一个节点temp
                flag= true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 可以删除
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除 %d 的节点不存在\n",no);
        }
    }

    /**
     * 求单链表中有效的个数
     */
    public static int getLength(HeroNode head){
        // 空链表
        if (head.next ==null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            // 遍历
            cur = cur.next;
        }
        return length;
    }

    /**
     * 得到倒数第 k 个节点
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 空链表
        if (head.next == null){
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;

        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 单链表反转
     */
    public static void reverseList(HeroNode head) {
        // 如果当前链表为空 或者只有一个节点 无需要反转 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 辅佐变量
        HeroNode cur = head.next;
        // 指向当前节点[cur] 的下一个节点
        HeroNode next = null;
        // 空节点
        HeroNode reverseHead = new HeroNode(0,"","");
        // 遍历链表
        while (cur != null) {
            // 先保存当前节点的下一个节点 因为后面需要使用
            next = cur.next;
            // 将cur的下一个节点指向新的链表的最前端
            cur.next = reverseHead.next;
            //
            reverseHead.next = cur;
            // 让cur后移
            cur = next;
        }
        // 将head.next 指向reverseHead.next 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 从尾到头打印单链表
     * 方法一: 先反转单链表再打印 缺点改变了单链表的结构、
     * 方法二：利用栈这个数据结构 将各个节点压入到栈中 然后利用栈的先进后出的特点就实现了逆序打印的效果
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        // 创建一个占栈 将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 将栈中的节点进行打印
        while (stack.size() > 0) {
            // 出栈
            System.out.println(stack.pop());
        }
    }

}


// 定义HeroNode 每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}