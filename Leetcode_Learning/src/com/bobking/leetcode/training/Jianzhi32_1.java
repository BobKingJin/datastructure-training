package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-06-26 14:54
 */
public class Jianzhi32_1 {

    public int[] levelOrder(TreeNode root) {

        if(root == null)
            return new int[0];

        // 按层遍历
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }

        int[] res = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);

        return res;
    }
}
