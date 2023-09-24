package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-06-04 20:06
 */
public class Number257 {

    public List<String> binaryTreePaths1(TreeNode root) {

        List<String> res = new ArrayList<String>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode root, String path, List<String> res) {

        // 如果为空，直接返回
        if (root == null)
            return;
        // 如果是叶子节点，说明找到了一条路径，把它加入到 res 中
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }

        // 如果不是叶子节点，在分别遍历左右子节点
        dfs(root.left, path + root.val + "->", res);
        dfs(root.right, path + root.val + "->", res);
    }

    public List<String> binaryTreePaths2(TreeNode root) {

        List<String> res = new ArrayList<String>();

        if (root == null)
            return res;
        // 先序遍历
        Stack<Object> stack = new Stack<Object>();

        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {

            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();

            if (node.left == null && node.right == null)
                res.add(path);

            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }

            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }

        return res;
    }

    public List<String> binaryTreePaths3(TreeNode root) {

        List<String> res = new ArrayList<String>();

        if (root == null)
            return res;

        Queue<Object> queue = new LinkedList<Object>();
        queue.add(root);
        queue.add(root.val + "");
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            String path = (String) queue.poll();

            if (node.left == null && node.right == null)
                res.add(path);

            if (node.right != null) {
                queue.add(node.right);
                queue.add(path + "->" + node.right.val);
            }

            if (node.left != null) {
                queue.add(node.left);
                queue.add(path + "->" + node.left.val);
            }
        }

        return res;
    }

    public List<String> binaryTreePaths4(TreeNode root) {

        List<String> res = new LinkedList<String>();

        if (root == null)
            return res;

        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }

        for (String path : binaryTreePaths4(root.left))
            res.add(root.val + "->" + path);

        for (String path : binaryTreePaths4(root.right))
            res.add(root.val + "->" + path);

        return res;
    }

}
