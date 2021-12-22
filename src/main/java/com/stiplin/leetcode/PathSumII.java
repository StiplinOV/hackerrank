package com.stiplin.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {

    public class TreeNode {
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

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        return visit(root, targetSum, new ArrayList<>());
    }

    public List<List<Integer>> visit(TreeNode root, int remaining, List<Integer> path) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> newPath = new ArrayList<>(path);
        newPath.add(root.val);
        if (root.left == null && root.right == null) {
            if (remaining == root.val) {
                result.add(new ArrayList<>(newPath));
                return result;
            } else {
                return result;
            }
        }
        if (root.left != null) {
            List<List<Integer>> leftPathes = visit(root.left, remaining - root.val, newPath);
            for (List<Integer> leftPath : leftPathes) {
                result.add(new ArrayList<>(leftPath));
            }
        }
        if (root.right != null) {
            List<List<Integer>> rightPathes = visit(root.right, remaining - root.val, newPath);
            for (List<Integer> rightPath : rightPathes) {
                result.add(new ArrayList<>(rightPath));
            }
        }
        return result;
    }

}
