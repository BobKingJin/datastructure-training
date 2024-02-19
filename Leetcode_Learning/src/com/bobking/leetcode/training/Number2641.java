package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2024/2/19 23:06
 * @Author: BobKing
 * @Description:
 */
public class Number2641 {

    // 参考: https://leetcode.cn/problems/cousins-in-binary-tree-ii/solutions/2229010/bfssuan-liang-ci-pythonjavacgo-by-endles-b72a/?envType=daily-question&envId=2024-02-19
    public TreeNode replaceValueInTree(TreeNode root) {

        root.val = 0;

        List<TreeNode> q = List.of(root);
        // 对于一个节点 x 来说，它的所有堂兄弟节点值的和，等于 x 这一层的所有节点值之和减去 x 及其兄弟节点的值之和
        while (!q.isEmpty()) {
            List<TreeNode> tmp = q;
            q = new ArrayList<TreeNode>();
            // 计算下一层的节点值之和
            int nextLevelSum = 0;
            for (TreeNode node : tmp) {
                if (node.left != null) {
                    q.add(node.left);
                    nextLevelSum += node.left.val;
                }
                if (node.right != null) {
                    q.add(node.right);
                    nextLevelSum += node.right.val;
                }
            }
            // 再次遍历，更新下一层的节点值
            for (TreeNode node : tmp) {
                int childrenSum = (node.left != null ? node.left.val : 0) +
                        (node.right != null ? node.right.val : 0);
                if (node.left != null)
                    node.left.val = nextLevelSum - childrenSum;
                if (node.right != null)
                    node.right.val = nextLevelSum - childrenSum;
            }
        }
        return root;
    }
}
