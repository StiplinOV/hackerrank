package com.stiplin.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class SymmetricTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root.left == null) {
            return root.right == null;
        }
        if (root.right == null) {
            return false;
        }
        if (root.left.val != root.right.val) {
            return false;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        Queue<TreeNode> leftQueue = new ArrayDeque<>();
        Queue<TreeNode> rightQueue = new ArrayDeque<>();
        leftQueue.add(left);
        rightQueue.add(right);
        while (!leftQueue.isEmpty() && !rightQueue.isEmpty()) {
            left = leftQueue.remove();
            right = rightQueue.remove();
            if (left.val != right.val) {
                return false;
            }
            if (left.left == null && right.right != null) {
                return false;
            }
            if (left.left != null && right.right == null) {
                return false;
            }
            if (left.left != null) {
                leftQueue.add(left.left);
                rightQueue.add(right.right);
            }

            if (left.right == null && right.left != null) {
                return false;
            }
            if (left.right != null && right.left == null) {
                return false;
            }
            if (left.right != null) {
                leftQueue.add(left.right);
                rightQueue.add(right.left);
            }
        }
        return true;
    }

}
