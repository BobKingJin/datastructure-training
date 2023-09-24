package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-07-09 17:57
 */
public class Number104 {

    // 参考：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/
    // dfs
    // 对比Number559
    public int maxDepth1(TreeNode root) {

        if (root == null) {
            return 0;
        } else {
            // 后序
            // 向父节点返回子节点的最长深度
            int leftHeight = maxDepth1(root.left);
            int rightHeight = maxDepth1(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // 参考：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/
    public int maxDepth2(TreeNode root) {

        if (root == null)
            return 0;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 按层遍历
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                size--;
            }
            res++;
        }
        return res;
    }


}
