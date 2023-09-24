package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-10 11:17
 */
public class LCP34 {

    // 参考：https://leetcode.cn/problems/er-cha-shu-ran-se-UGC/solution/dp-by-hu-li-hu-wai-rr0c/
    public int maxValue(TreeNode root, int k) {

        // 自底向上 dp，保存子节点的状态并计算当前是否染色的状态
        // dp[i] 表示 每个节点的状态，i 表示染了几个节点，i = 0 表示没有染色，i > 0 表示染色
        // 当前节点为 root，dp逻辑为：
        // root不染色，那么只要返回 dp[0]，其值为左、右子树染色或不染色的最大值之和
        // root染色，那么就分左子树染色 j 个，右子树染色 i - 1 - j 个时，加上 root.val 的和
        // 注意：j 需要从 0 取到 i - 1，也就是包含 l[0] 和 r[0]
        // 因为 l[0] 也包含左子树染了 j 个节点的情况，因为左子树的下一层子节点可能染了j个节点
        // dp[i] = Math.max(dp[i], root.val + l[j] + r[i - 1 - j]);

        int[] dp = dynamic(root, k);
        int max = Integer.MIN_VALUE;

        for (int i = 0; i <= k; i++)
            max = Math.max(max, dp[i]);

        return max;
    }

    private int[] dynamic(TreeNode root, int k) {

        int[] dp = new int[k + 1];
        // 1.初始化：空节点为底，自底向上
        if (root == null)
            return dp;
        // 2.获取左、右子树染色状态的 dp 表
        // 左子树
        int[] l = dynamic(root.left, k);
        // 右子树
        int[] r = dynamic(root.right, k);
        // 3.更新处理 root 染色/不染色 的情况下的dp表
        // 不染 root
        int ml = Integer.MIN_VALUE;
        int mr = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            // 分别取子节点的最大值
            ml = Math.max(ml, l[i]);
            mr = Math.max(mr, r[i]);
        }
        dp[0] = ml + mr;
        // 染 root
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < i; j++)
                // 还需要染色 i - 1 个点，左子树 j 个，右子树 i - 1 - j 个
                dp[i] = Math.max(dp[i], root.val + l[j] + r[i - 1 - j]);
        }
        // 4.更新完毕，返回后继续向上动态规划
        return dp;
    }
}
