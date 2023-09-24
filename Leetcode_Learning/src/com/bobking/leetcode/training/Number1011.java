package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-24 10:20
 */
public class Number1011 {

    // 参考：https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/solution/gong-shui-san-xie-li-yong-er-duan-xing-z-95zj/
    public int shipWithinDays(int[] weights, int days) {

        int max = 0;
        int sum = 0;
        for (int w : weights) {
            max = Math.max(max, w);
            sum += w;
        }

        int l = max;
        int r = sum;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(weights, mid, days)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    private boolean check(int[] weights, int t, int days) {
        int n = weights.length;
        int cnt = 1;
        for (int i = 1, sum = weights[0]; i < n; sum = 0, cnt++) {
            while (i < n && sum + weights[i] <= t) {
                sum += weights[i];
                i++;
            }
        }
        return cnt - 1 <= days;
    }
}
