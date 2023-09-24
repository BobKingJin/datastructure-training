package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2023-04-02 19:51
 */
public class Number2583 {

    public long kthLargestLevelSum(TreeNode root, int k) {

        if (root == null)
            return -1;
        ArrayList<Long> arr = new ArrayList<Long>();
        Deque<TreeNode> que = new ArrayDeque<TreeNode>();
        que.addFirst(root);

        while (que.isEmpty() == false) {
            int num = que.size();
            long sum = 0;
            for (int i = 0; i < num; i++) {
                TreeNode temp = que.pollLast();
                sum += temp.val;
                if (temp.left != null)
                    que.addFirst(temp.left);
                if (temp.right != null)
                    que.addFirst(temp.right);
            }
            arr.add(sum);
        }
        arr.sort(Comparator.naturalOrder());
        if (arr.size() >= k) {
            return arr.get(arr.size() - k);
        } else {
            return -1;
        }
    }
}
