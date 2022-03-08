package com.fzn.day15;

/**
 * @program: DataStructure
 * 描述： 二叉排序树
 * @author: fzn
 * @create: 2022-02-14 16:47
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,0};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        // 中序遍历二叉排序树
        binarySortTree.infixOrder();
        System.out.println("===========");
        // 测试删除叶子节点
        binarySortTree.delNode(1);
        binarySortTree.infixOrder();

    }
}
// 二叉排序树
class BinarySortTree {
    private Node root;
    //查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        }else {
            return root.search(value);
        }
    }
    // 查找父结点
    public Node searchParent(int value){
        if (root == null) {
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    // 1. 返回以node为根节点的二叉树的最小节点值
    // 2. 删除node为根节点的二叉排序数树的最小结点
    /**
     *
     * @param node 传入的节点 当做二叉排序树的根节点
     * @return 返回以node为根节点的二叉树的最小节点值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环的查找左子节点 就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这时target就指向最小结点 删除最小结点
        delNode(target.value);
        return target.value;
    }

    // 删除节点
    public void delNode(int value) {
        if(root == null){
            return;
        }else {
            // 先去找到要删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这颗二叉排序数树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 去找到targetNode的父节点
            Node parent = searchParent(value);
            // 如果要删除的是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode 是父结点的左子节点还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    // 是左子节点
                    parent.left = null;
                }else if (parent.right != null && parent.right.value == value) {
                    // 是右子节点
                    parent.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null) {
                // 左右都有子树
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else {
                // 只有一颗子树的节点
                // 如果要删除的节点有左子节点
                if (targetNode.left != null){
                    if (parent != null){
                        // 如果targetNode 是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        }else {
                            // targetNode 是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }

                }else {
                    // 如果要删除的节点有右子节点
                    if (parent != null){
                        // 如果targetNode 是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        }else {
                            // targetNode 是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }

                }
            }
        }
    }

    // 添加节点的方法
    public void add(Node node) {
        if (root == null) {
            // 如果root为空则直接让root指向node
            root = node;
        }else {
            root.add(node);
        }
    }
    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空");
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 查找要删除的结点
    /**
     *
     * @param value 希望删除节点的值
     * @return 如果找到返回该节点否则返回null
     */
    public Node search(int value) {
        if (value == this.value) { // 找到
            return this;
        }else if (value < this.value) { // 如果查找的值小于当前节点 向左子树递归查找
            // 如果左子节点为空
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        }else {
            // 如果大于 向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父节点
    /**
     *
     * @param value 要找到节点的值
     * @return 返回的是要删除结点的父节点 如果没有就返回null
     */
    public Node searchParent(int value) {
        // 如果当前节点就是要删除的节点的父结点就返回
        if ((this.left != null && this.left.value == value)
            || (this.right != null && this.right.value == value)) {
            return this;
        }else {
            // 如果查找的值小于当前节点的值 并且当前节点的左子节点不为空
            if(value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            }else if(value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            }else {
                // 没有找到父节点
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 添加节点的方法
    // 递归的形式添加节点 需要满足二叉树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 传入的节点的值 和当前子树和根节点的值关系
        if (node.value < this.value) {
            // 如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            // 添加节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}