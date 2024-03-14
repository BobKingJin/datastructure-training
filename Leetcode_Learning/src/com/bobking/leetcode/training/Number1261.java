package com.bobking.leetcode.training;

/**
 * @Date: 2024/3/12 7:52
 * @Author: BobKing
 * @Description:
 */
public class Number1261 {

    private class FindElements {

        TreeNode rootNode;

        public FindElements(TreeNode root) {
            dfs(root, 0);
        }

        private void dfs(TreeNode node, int val) {

            if (node == null)
                return;

            node.val = val;
            dfs(node.left, val * 2 + 1);
            dfs(node.right, val * 2 + 2);
            this.rootNode = node;
        }

        // 参考: https://leetcode.cn/problems/find-elements-in-a-contaminated-binary-tree/solutions/48415/bu-yong-setde-findfang-fa-by-cyanflxy/?envType=daily-question&envId=2024-03-12
        public boolean find(int target) {

            if (target < 0)
                return false;

            TreeNode node = rootNode;
            // 将 target 加 1，用以表示转换树中的值
            target++;
            // 找到次高位开始计算
            int bit = Integer.highestOneBit(target) >> 1;

            while (bit > 0 && node != null) {
                if ((target & bit) == 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
                bit >>= 1;
            }

            return node != null;
        }
    }
}
