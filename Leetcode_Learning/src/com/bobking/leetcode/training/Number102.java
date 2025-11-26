package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number102 {

    // 参考：程序猿代码指南P132
    // bfs
    public List<List<Integer>> levelOrder1(TreeNode root) {

        // 主要问题: 何时换行?
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (root == null) {
            return res;
        }

        List<Integer> list = new ArrayList<Integer>();
        // last为当前行的最右节点
        TreeNode last = root;
        // nLast为下一行的最右节点
        TreeNode nLast = null;
        // 利用队列实现 bfs
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            root = queue.poll();
            list.add(root.val);
            if (root.left != null) {
                queue.offer(root.left);
                nLast = root.left;
            }
            if (root.right != null) {
                queue.offer(root.right);
                nLast = root.right;
            }
            if (root == last) {
                res.add(list);
                list = new ArrayList<Integer>();
                last = nLast;
            }
        }
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
    // bfs
    public List<List<Integer>> levelOrder2(TreeNode root) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            int n = queue.size();
            for (int i = 1; i <= n; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }
}
