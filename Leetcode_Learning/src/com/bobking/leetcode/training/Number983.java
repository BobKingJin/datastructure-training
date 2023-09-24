package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-10 20:20
 */
public class Number983 {

    // 参考：https://leetcode.cn/problems/minimum-cost-for-tickets/solution/java-dong-tai-gui-hua-si-lu-bu-zou-cong-hou-xiang-/
    public int mincostTickets(int[] days, int[] costs) {

        // 「今天买多少，得看后几天怎么安排」，即「前面依赖后面」——从后向前来买

        int len = days.length;
        int maxDay = days[len - 1];
        int minDay = days[0];
        // 多扩几天，省得判断 365 的限制
        int[] dp = new int[maxDay + 31];

        // 只需看 maxDay -> minDay，此区间外都不需要出门，不会增加费用
        for (int d = maxDay, i = len - 1; d >= minDay; d--) {
            // i 表示 days 的索引
            // 也可提前将所有 days 放入 Set，再通过 set.contains() 判断
            if (d == days[i]) {
                dp[d] = Math.min(dp[d + 1] + costs[0], dp[d + 7] + costs[1]);
                dp[d] = Math.min(dp[d], dp[d + 30] + costs[2]);
                // 别忘了递减一天
                i--;
            } else {
                // 不需要出门
                dp[d] = dp[d + 1];
            }
        }
        // 从后向前遍历，返回最前的 minDay
        return dp[minDay];
    }
}
