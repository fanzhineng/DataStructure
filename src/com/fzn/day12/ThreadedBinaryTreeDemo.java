package com.fzn.day12;

/**
 * @program: DataStructure
 * 描述：
 * @author: fzn
 * @create: 2022-02-09 13:23
 **/
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        // 创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 测试线索化
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threadedNodes();

        // 测试： 10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱结点"+leftNode); // 3
        System.out.println("10号节点的前后继结点"+rightNode); // 1

        System.out.println("线索化遍历");
        binaryTree.threadedList();

    }
}

// ThreadedBinaryTree 实现线索化功能的二叉树
class BinaryTree {
    // 根节点
    private HeroNode root;

    // 为了实现线索化 需要创建要给指向当前结点的前驱结点的指针
    // 在递归进行线索化的时候 pre 总是指向前一个结点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 遍历线索化二叉树的方法
    public void threadedList() {
        // 定义一个变量 存储当前遍历的结点从root开始
        HeroNode node = root;
        while (node != null) {
            // 循环的找到leftType==1的结点 第一个找到的就是8的节点
            // 后面遍历会变化 因为leftType==1时 说明该结点按照线索化
            // 处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前这个结点
            System.out.println(node);
            // 如果当前节点的右指针指向的时候后继节点 就一直输出
            while (node.getRightType() == 1) {
                // 获取当前节点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的结点
            node = node.getRight();
        }
    }

    // 重载
    public void threadedNodes() {
        threadedNodes(root);
    }
    // 编写对二叉树线索化中序线索化的方法
    public void threadedNodes(HeroNode node) {
        // 如果当前节点为null 不能线索化
        if (node == null) {
            return;
        }

        // 先线索化左子树
        threadedNodes(node.getLeft());

        // 线索化当前节点
        // 先处理当前节点的前驱结点
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱结点
            node.setLeft(pre);
            // 修改当前左指针类型 指向前驱结点
            node.setLeftType(1);
        }

        // 处理后继结点
        if (pre != null && pre.getRight() == null) {
            // 让前驱结点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱结点的右指针类型
            pre.setRightType(1);
        }

        // 每处理一个结点后 让当前结点的是下一个结点的前驱结点
        pre = node;

        // 再线索化右子树
        threadedNodes(node.getRight());
    }


    // 删除结点
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root结点 这里立即判断root是不是就是要删除结点
            if (root.getNo() == no) {
                root = null;
            }else {
                // 递归删除
                root.deNode(no);
            }
        }else {
            System.out.println("空数不能删除");
        }
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空 无法遍历");
        }
    }
    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空 无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空 无法遍历");
        }
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}

// 创建HeroNode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left; // 默认为null
    private HeroNode right;// 默认为null

    // 如果leftType==0 表示指向的是左子树 如果leftType==1 则表示指向前驱结点
    private int leftType;
    // 如果 rightType==0 表示指向的是右子树 如果rightType==1 则表示指向后继结点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 递归删除节点
    public void deNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 向左子树进行递归删除
        if (this.left != null) {
            this.left.deNode(no);
        }
        if (this.right != null) {
            this.right.deNode(no);
        }
    }

    // 编写前序遍历的方法
    public void preOrder() {
        System.out.println(this); // 先输出父节点
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 先输出父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        // 递归向左子树后序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树后序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 先输出父节点
        System.out.println(this);
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        // 比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        // 则判断当前结点的左子节点是否为空 如果不为空 则递归前序查找
        // 如果找到就返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            // 说明左子树找到
            return resNode;
        }
        // 否则继续判断当前右子节点是否为空 如果不为空 则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        // 可能为空 也可能不为空
        return resNode;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        // 先判断当前的左子树节点是否为空 如果不为空 则递归中序遍历
        HeroNode resNode  = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 和当前节点比较
        if (this.no == no) {
            return this;
        }
        // 否则向右继续中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode  = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        // return resNode;
        return null;
    }
}