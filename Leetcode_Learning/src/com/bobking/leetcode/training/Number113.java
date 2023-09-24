package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-05-20 10:39
 */
public class Number113 {

    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs1(root, targetSum, new ArrayList<Integer>(), result);
        return result;
    }

    // 先序遍历
    private void dfs1(TreeNode root, int targetSum, List<Integer> list, List<List<Integer>> result) {

        if (root == null)
            return;

        // 每次都是重新建一个 list
        // 因此最后不存在回溯
        List<Integer> subList = new ArrayList<Integer>(list);

        subList.add(root.val);

        // 叶子节点
        if (root.left == null && root.right == null) {

            if (targetSum == root.val)
                result.add(subList);
            return;
        }

        dfs1(root.left, targetSum - root.val, subList, result);
        dfs1(root.right, targetSum - root.val, subList, result);
    }

    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs2(root, targetSum, new ArrayList<Integer>(), res);
        return res;
    }

    // 先序遍历
    private void dfs2(TreeNode root, int targetSum, List<Integer> list, List<List<Integer>> res) {

        if (root == null)
            return;

        list.add(root.val);

        if (root.left == null && root.right == null) {

            if (targetSum == root.val)
                // 每次都是重新构建一个 list
                res.add(new ArrayList(list));

            list.remove(list.size() - 1);
            return;
        }

        dfs2(root.left, targetSum - root.val, list, res);
        dfs2(root.right, targetSum - root.val, list, res);
        // 回溯
        list.remove(list.size() - 1);
    }

    public List<List<Integer>> pathSum3(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        dfs3(root, targetSum, 0, new ArrayList<Integer>(), result);
        return result;
    }

    public void dfs3(TreeNode root, int targetSum, int toal, List<Integer> list, List<List<Integer>> result) {

        if (root == null)
            return;

        list.add(root.val);

        toal += root.val;

        if (root.left == null && root.right == null) {

            if (targetSum == toal)
                result.add(new ArrayList(list));

            list.remove(list.size() - 1);
            return;
        }

        dfs3(root.left, targetSum, toal, list, result);
        dfs3(root.right, targetSum, toal, list, result);

        list.remove(list.size() - 1);
    }

    public List<List<Integer>> pathSum4(TreeNode root, int targetSum) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (root == null)
            return res;

        Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
        Queue<List<Integer>> queueList = new LinkedList<List<Integer>>();

        queueNode.add(root);

        List<Integer> list = new ArrayList<Integer>();
        list.add(root.val);
        queueList.add(list);

        while (!queueNode.isEmpty()) {

            TreeNode node = queueNode.poll();

            List<Integer> tempList = queueList.poll();
            if (node.left == null && node.right == null && node.val == targetSum)
                res.add(tempList);

            if (node.left != null) {
                tempList.add(node.left.val);
                queueList.add(new ArrayList<Integer>(tempList));
                node.left.val += node.val;
                queueNode.add(node.left);
                tempList.remove(tempList.size() - 1);
            }

            if (node.right != null) {
                tempList.add(node.right.val);
                queueList.add(new ArrayList<Integer>(tempList));
                node.right.val += node.val;
                queueNode.add(node.right);
            }
        }
        return res;
    }



}
