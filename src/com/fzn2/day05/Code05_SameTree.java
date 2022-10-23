package com.fzn2.day05;

/**
 * @program: DataStructure
 * 描述：  https://leetcode.cn/problems/same-tree
 *      相同的二叉树
 * @author: fzn
 * @create: 2022-10-23 09:01
 **/
public class Code05_SameTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        // 都不为空
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}