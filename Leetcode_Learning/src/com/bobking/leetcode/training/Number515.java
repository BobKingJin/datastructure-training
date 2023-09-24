package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-07-24 20:51
 */
public class Number515 {

    // 参考：程序猿代码指南P132
    // 二叉树按层遍历
    public List<Integer> largestValues1(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        // last为当前行的最右节点
        TreeNode last = root;
        // nLast为下一行的最右节点
        TreeNode nLast = null;
        // 利用队列实现 bfs
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        // 每一层节点最大值
        int levelMax = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {

            root = queue.poll();
            levelMax = Math.max(root.val, levelMax);

            if (root.left != null) {
                queue.offer(root.left);
                nLast = root.left;
            }
            if (root.right != null) {
                queue.offer(root.right);
                nLast = root.right;
            }

            if (root == last) {
                res.add(levelMax);
                levelMax = Integer.MIN_VALUE;
                last = nLast;
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/solution/javadai-ma-bfshe-dfsliang-chong-jie-jue-si-lu-yi-j/
    // dfs
    public List<Integer> largestValues2(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();

        if (root == null)
            return res;

        dfs(root, res, 1);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res, int level) {

        if (root == null)
            return;

        // 先序遍历
        // 如果走到下一层了直接加入到集合中
        if (level == res.size() + 1) {
            res.add(root.val);
        } else {
            // 注意：level是从 1 开始的，也就是说 root 是第一层，而集合 list 的下标是从 0 开始的
            // 所以这里 level 要减 1
            // Math.max(res.get(level - 1), root.val)表示的是遍历到的第level层的root.val值
            // 和集合中的第 level 个元素的值哪个大，就要哪个
            res.set(level - 1, Math.max(res.get(level - 1), root.val));
        }

        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }

}
