package com.fzn.day03;


/**
 * @program: DataStructure
 * 描述：
 * @author: fzn
 * @create: 2022-01-17 16:12
 **/
public class DoubleLinkListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "黄建飞", "傻儿子");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        // 测试
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        // add
        doubleLinkList.add(hero1);
        doubleLinkList.add(hero2);
        doubleLinkList.add(hero3);
        doubleLinkList.add(hero4);

        // list
        doubleLinkList.list();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "范知能", "小范");
        doubleLinkList.update(newHeroNode);
        System.out.println("------修改之后的----");
        doubleLinkList.list();

        // 删除
        doubleLinkList.del(3);
        System.out.println("------删除之后的----");
        doubleLinkList.list();
    }
}

// 双向链表
class DoubleLinkList {
    // head节点
    private HeroNode2 head = new HeroNode2(0,"","");

    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }
    // 显示链表[遍历] s顺序
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量
        HeroNode2 temp = head.next;
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

    // 添加节点到双向链表
    // 1.找到当前链表的最后节点
    // 2.将最后这个节点的next 指向 新的节点
    public void add(HeroNode2 heroNode) {
        // 因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode2 temp = head;
        // 遍历链表 找到最后
        while (true) {
            if (temp.next == null){
                // 最后一个
                break;
            }
            // 如果没有找到最后 就将temp 后移
            temp= temp.next;
        }
        // 当退出while循环之后， temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 修改节点的信息，根据no编号的修改，即no编号 不能修改
    // 根据newHereNode 的no 来修改即可
    // 双向链表的修改和单向链表一样
    public void update(HeroNode2 newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 定义一个辅助变量
        HeroNode2 temp = head;
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
        // 空链表
        if (head.next == null) {
            System.out.println("链表为空无法删除");
            return;
        }
        // 辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            // 已经到链表的最后
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                // 找到的待删除节点的前一个节点temp
                flag= true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 可以删除 重新形成双向链表
            temp.pre.next = temp.next;
            // 这句话 temp不能是最后的节点 不然会空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除 %d 的节点不存在\n",no);
        }
    }


}

// 定义HeroNode 每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 pre; // 指向上一个节点

    public HeroNode2(int no, String name, String nickname) {
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