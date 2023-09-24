package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-07-24 21:06
 */
public class Number637 {

    // 参考：程序猿代码指南P132
    // bfs
    public List<Double> averageOfLevels1(TreeNode root) {

        List<Double> res = new ArrayList<Double>();

        if (root == null)
            return res;

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

            if (root.left != null) {
                queue.offer(root.left);
                nLast = root.left;
            }
            if (root.right != null) {
                queue.offer(root.right);
                nLast = root.right;
            }

            list.add(root.val);
            if (root == last) {

                double sum = 0L;
                for (Integer i : list)
                    sum += i;

                res.add(sum / list.size());
                list = new ArrayList<Integer>();
                last = nLast;
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/solution/er-cha-shu-de-ceng-ping-jun-zhi-by-leetcode-soluti/
    // dfs
    public List<Double> averageOfLevels(TreeNode root) {

        // counts 用于存储二叉树的每一层的节点数
        // sums 用于存储二叉树的每一层的节点值之和
        List<Integer> counts = new ArrayList<Integer>();
        List<Double> sums = new ArrayList<Double>();
        dfs(root, 0, counts, sums);

        List<Double> averages = new ArrayList<Double>();
        for (int i = 0; i < sums.size(); i++)
            averages.add(sums.get(i) / counts.get(i));

        return averages;
    }

    private void dfs(TreeNode root, int level, List<Integer> counts, List<Double> sums) {

        if (root == null)
            return;

        // 先序遍历
        if (level < sums.size()) {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        } else {
            sums.add(1.0 * root.val);
            counts.add(1);
        }

        dfs(root.left, level + 1, counts, sums);
        dfs(root.right, level + 1, counts, sums);
    }
}
