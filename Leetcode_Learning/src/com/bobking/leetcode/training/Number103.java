package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Number103 {

    // 参考：程序猿代码指南P134
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (root == null)
            return res;

        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();
        TreeNode last = root;
        TreeNode nLast = null;
        // leftToRight 为 true 时，那么在压入孩子节点时，应该先左后右
        boolean leftToRight = true;
        queue.offerFirst(root);

        while (!queue.isEmpty()) {

            if (leftToRight) {
                // 从头弹出
                root = queue.pollFirst();
                if (root.left != null) {
                    nLast = nLast == null ? root.left : nLast;
                    queue.offerLast(root.left);
                }
                if (root.right != null) {
                    nLast = nLast == null ? root.right : nLast;
                    queue.offerLast(root.right);
                }
            } else {
                // 从尾弹出
                root = queue.pollLast();
                if (root.right != null) {
                    nLast = nLast == null ? root.right : nLast;
                    queue.offerFirst(root.right);
                }
                if (root.left != null) {
                    nLast = nLast == null ? root.left : nLast;
                    queue.offerFirst(root.left);
                }
            }

            list.add(root.val);
            // 当遍历到最后一层时，root == last，此时队列为空，而最后一个list还没有加入res中
            if (root == last && !queue.isEmpty()) {
                leftToRight = !leftToRight;
                last = nLast;
                nLast = null;
                res.add(list);
                list = new ArrayList<Integer>();
            }
        }
        // 最后一个list
        res.add(list);

        return res;
    }
}
