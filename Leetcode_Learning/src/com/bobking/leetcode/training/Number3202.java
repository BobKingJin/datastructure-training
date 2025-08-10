package com.bobking.leetcode.training;

/**
 * @Date: 2025/8/9 23:32
 * @Author: BobKing
 * @Description:
 */
public class Number3202 {

    // 参考: https://leetcode.cn/problems/find-the-maximum-length-of-valid-subsequence-ii/solutions/2826591/deng-jie-zhuan-huan-dong-tai-gui-hua-pyt-z2fs/
    // 参考: https://www.bilibili.com/video/BV16w4m1e7y3/?vd_source=d03a75be00793d738878cc7fec840522
    public int maximumLength(int[] nums, int k) {
        int ans = 0;
        int[][] f = new int[k][k];
        for (int x : nums) {
            x %= k;
            for (int y = 0; y < k; y++) {
                f[y][x] = f[x][y] + 1;
                ans = Math.max(ans, f[y][x]);
            }
        }
        return ans;
    }

}
