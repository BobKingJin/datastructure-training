package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-08-27 7:59
 */
public class Number662 {

    public int widthOfBinaryTree(TreeNode root) {

        if(root == null)
            return 0;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        LinkedList<Integer> list = new LinkedList<Integer>();
        q.offer(root);
        list.add(1);
        int res = 1;

        while (!q.isEmpty()) {
            int count = q.size();
            for(int i =count; i> 0; i--) {
                TreeNode cur = q.poll();
                Integer curIndex = list.removeFirst();
                if(cur.left != null) {
                    q.offer(cur.left);
                    list.add(curIndex * 2);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                    list.add(curIndex * 2 +1);
                }
            }
            // list 中 size 为 1 的情况下，宽度也为 1，没有必要计算。
            if(list.size() >= 2)
                res = Math.max(res, list.getLast() - list.getFirst() + 1);
        }

        return res;
    }
}
