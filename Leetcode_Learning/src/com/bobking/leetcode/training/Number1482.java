package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-29 23:55
 */
public class Number1482 {

    private int n;
    private int m;
    private int k;
    private boolean[] fl;

    // 参考：https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets/solution/gong-shui-san-xie-li-yong-er-duan-xing-z-ysv4/
    public int minDays(int[] bloomDay, int m, int k) {

        n = bloomDay.length;
        this.m = m;
        this.k = k;

        if (n < m * k)
            return -1;

        fl = new boolean[n];

        int l = 0;
        int r = (int)1e9;

        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(bloomDay, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return check(bloomDay, r) ? r : -1;
    }

    private boolean check(int[] nums, int mid) {

        for (int i = 0; i < n; i++)
            fl[i] = nums[i] <= mid;

        int cnt = 0;

        for (int i = 0; i < n && cnt < m; ) {
            if (fl[i]) {
                int cur = 1;
                int j = i;
                while (cur < k && j + 1 < n && fl[j + 1]) {
                    j++;
                    cur++;
                }

                if (cur == k)
                    cnt++;

                i = j + 1;
            } else {
                i++;
            }
        }

        return cnt >= m;
    }
}
