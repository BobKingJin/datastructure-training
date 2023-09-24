package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-21 20:54
 */
public class Number1326 {

    // 参考：https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/solution/python3javacgotypescript-yi-ti-yi-jie-ta-hwfe/
    public int minTaps(int n, int[] ranges) {

        int[] last = new int[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            int l = Math.max(0, i - ranges[i]);
            int r = i + ranges[i];
            last[l] = Math.max(last[l], r);
        }

        int ans = 0;
        int mx = 0;
        int pre = 0;

        for (int i = 0; i < n; ++i) {

            mx = Math.max(mx, last[i]);

            if (mx <= i)
                return -1;

            if (pre == i) {
                ++ans;
                pre = mx;
            }
        }
        return ans;
    }
}
