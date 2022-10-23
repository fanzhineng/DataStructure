package com.fzn2.day05;

/**
 * @program: DataStructure
 * 描述：  返回一颗树的最大高度（深度）   二叉树的最大深度
 * https://leetcode.com/problems/maximum-depth-of-binary-tree
 * @author: fzn
 * @create: 2022-10-23 09:22
 **/
public class Code07_MaximumDepthOfBinaryTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
    }

    // 以root为头的树，最大高度是多少返回！
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}