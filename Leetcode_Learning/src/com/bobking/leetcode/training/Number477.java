package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-19 11:12
 */
public class Number477 {

    // 参考：https://leetcode.cn/problems/total-hamming-distance/solution/gong-shui-san-xie-ying-yong-cheng-fa-yua-g21t/
    public int totalHammingDistance(int[] nums) {

        int ans = 0;
        for (int x = 31; x >= 0; x--) {
            int s0 = 0;
            int s1 = 0;
            for (int u : nums) {
                if (((u >> x) & 1) == 1) {
                    s1++;
                } else {
                    s0++;
                }
            }
            ans += s0 * s1;
        }
        return ans;
    }
}
