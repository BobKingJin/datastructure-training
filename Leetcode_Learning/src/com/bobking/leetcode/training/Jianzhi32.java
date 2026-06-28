package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2026/6/28 15:53
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi32 {

    // 参考: Number102
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (root == null) {
            return ans;
        }

        // 按层遍历
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return ans;
    }

}
