package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2023-11-05 7:50
 */
public class LCR151 {

    public List<List<Integer>> decorateRecord1(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (root != null)
            queue.add(root);

        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<Integer>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                // 偶数层 则添加至 tmp 头部
                if (res.size() % 2 == 0) {
                    tmp.addLast(node.val);
                } else {  // 奇数层 则添加至 tmp 尾部
                    tmp.addFirst(node.val);
                }

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }

    public List<List<Integer>> decorateRecord2(TreeNode root) {

        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (root != null)
            deque.add(root);

        while (!deque.isEmpty()) {
            // 打印奇数层
            List<Integer> tmp = new ArrayList<Integer>();
            for (int i = deque.size(); i > 0; i--) {
                // 从左向右打印
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                // 先左后右加入下层节点
                if (node.left != null)
                    deque.addLast(node.left);
                if (node.right != null)
                    deque.addLast(node.right);
            }
            res.add(tmp);
            if (deque.isEmpty())
                // 若为空则提前跳出
                break;

            // 打印偶数层
            tmp = new ArrayList<Integer>();
            for (int i = deque.size(); i > 0; i--) {
                // 从右向左打印
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // 先右后左加入下层节点
                if (node.right != null)
                    deque.addFirst(node.right);
                if (node.left != null)
                    deque.addFirst(node.left);
            }
            res.add(tmp);
        }
        return res;
    }

}
