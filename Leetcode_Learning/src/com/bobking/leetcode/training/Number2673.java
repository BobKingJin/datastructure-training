package com.bobking.leetcode.training;

/**
 * @Date: 2024/2/29 0:02
 * @Author: BobKing
 * @Description:
 */
public class Number2673 {

    // 参考: https://leetcode.cn/problems/make-costs-of-paths-equal-in-a-binary-tree/solutions/2259983/tan-xin-jian-ji-xie-fa-pythonjavacgo-by-5svh1/?envType=daily-question&envId=2024-02-28
    public int minIncrements(int n, int[] cost) {

        int ans = 0;
        // 从最后一个非叶节点开始算
        for (int i = n / 2; i > 0; i--) {
            // 两个子节点变成一样的
            ans += Math.abs(cost[i * 2 - 1] - cost[i * 2]);
            // 累加路径和
            cost[i - 1] += Math.max(cost[i * 2 - 1], cost[i * 2]);
        }
        return ans;
    }
}
