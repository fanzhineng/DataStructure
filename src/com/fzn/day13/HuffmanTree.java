package com.fzn.day13;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @program: DataStructure
 * 描述：  赫夫曼树
 * @author: fzn
 * @create: 2022-02-10 15:33
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    // 编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }else {
            System.out.println("是空树 不能遍历");
        }
    }
    // 创建赫夫曼树
    public static Node createHuffmanTree(int[] arr) {
        // 遍历arr数组 将arr的每个元素构成一个Node
        // 将Node 放入到ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {

            // 排序 从小到大排序
            Collections.sort(nodes);

            // System.out.println(nodes);

            // 取出根节点权值最小的两颗二叉树
            // 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            // 取出第二小的结点
            Node rightNode = nodes.get(1);

            // 构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 将parent 加入 nodes
            nodes.add(parent);
            // System.out.println(nodes);
        }

        return nodes.get(0);
    }
}

// 创建结点类
class Node implements  Comparable<Node>{
    // 结点权值
    int value;
    // 指向左子节点
    Node left;
    // 指向右子节点
    Node right;

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }
}