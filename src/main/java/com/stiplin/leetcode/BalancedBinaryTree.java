package com.stiplin.leetcode;

import sun.reflect.generics.tree.Tree;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class BalancedBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private static class Pair {

        boolean isBalansed;

        int height;

        Pair(boolean isBalansed, int height) {
            this.isBalansed = isBalansed;
            this.height = height;
        }

    }

    public boolean isBalanced(TreeNode root) {
        return getPair(root, 0).isBalansed;
    }

    private Pair getPair(TreeNode node, int depth) {
        if (node == null) {
            return new Pair(true, depth);
        }
        Pair leftPair = getPair(node.left, depth + 1);
        Pair rightPair = getPair(node.right, depth + 1);
        return new Pair(Math.abs(leftPair.height - rightPair.height) <= 1 && leftPair.isBalansed && rightPair.isBalansed, Math.max(leftPair.height, rightPair.height));
    }

}
