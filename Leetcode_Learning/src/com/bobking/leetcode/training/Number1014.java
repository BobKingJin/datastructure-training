package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-03 11:59
 */
public class Number1014 {

    // 参考：https://leetcode.cn/problems/best-sightseeing-pair/solution/zui-jia-guan-guang-zu-he-by-leetcode-solution/
    public int maxScoreSightseeingPair(int[] values) {

        int ans = 0;
        int mx = values[0] + 0;

        for (int j = 1; j < values.length; ++j) {
            ans = Math.max(ans, mx + values[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, values[j] + j);
        }

        return ans;
    }
}
