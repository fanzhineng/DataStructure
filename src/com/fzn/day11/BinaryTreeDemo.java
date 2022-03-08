package com.fzn.day11;

/**
 * @program: DataStructure
 * 描述：      二叉树的前序 中序 后序遍历 查找
 *              删除
 * @author: fzn
 * @create: 2022-02-07 15:00
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");

        // 先手动创建二叉树 后面使用递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);

        binaryTree.setRoot(root);
        // 前序遍历
        // System.out.println("前序遍历");
        // binaryTree.preOrder();
        //
        // // 中序遍历
        // System.out.println("中序遍历");
        // binaryTree.infixOrder();
        //
        // // 后序遍历
        // System.out.println("后序遍历");
        // binaryTree.postOrder();
        //
        // // 前序遍历方式
        // System.out.println("前序遍历方式");
        // int no = 3;
        // HeroNode result = binaryTree.preOrderSearch(no);
        // if (result != null) {
        //     System.out.printf("找到了，信息为no=%d name=%s",result.getNo(),result.getName());
        // }else {
        //     System.out.printf("没有找到no=%d的",no);
        // }

        System.out.println("删除前, 前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(4);
        System.out.println("删除后, 前序遍历");
        binaryTree.preOrder();



    }
}
// 定义一个二叉树
class BinaryTree {
    // 根节点
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode  infixOrderSearch(int no) {
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

// 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left; // 默认为null
    private HeroNode right;// 默认为null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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