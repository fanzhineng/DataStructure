package com.fzn2.day05;

/**
 * @program: DataStructure
 * 描述：  https://leetcode.com/problems/symmetric-tree
 *  镜面树    对称二叉树
 * @author: fzn
 * @create: 2022-10-23 09:11
 **/
public class Code06_SymmetricTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public static boolean isMirror(TreeNode h1, TreeNode h2) {
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        return h1.val == h2.val && isMirror(h1.left, h2.right) && isMirror(h1.right, h2.left);
    }
}