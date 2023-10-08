package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-08 7:38
 */
public class Number979 {

    private int ans;

    // 参考: https://leetcode.cn/problems/distribute-coins-in-binary-tree/solutions/2343262/tu-jie-mei-you-si-lu-jin-lai-miao-dong-p-vrni/?envType=daily-question&envId=2023-10-08
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {

        if (node == null)
            return new int[]{0, 0};
        // 后续遍历
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        // 子树硬币个数
        int coins = left[0] + right[0] + node.val;
        // 子树节点数
        int nodes = left[1] + right[1] + 1;
        ans += Math.abs(coins - nodes);
        return new int[]{coins, nodes};
    }
}
