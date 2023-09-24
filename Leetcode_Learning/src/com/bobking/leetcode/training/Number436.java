package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-09 20:38
 */
public class Number436 {

    // 参考：https://leetcode.cn/problems/find-right-interval/solution/by-ac_oier-sijp/
    public int[] findRightInterval1(int[][] intervals) {

        int n = intervals.length;
        int[][] clone = new int[n][2];
        for (int i = 0; i < n; i++)
            clone[i] = new int[]{intervals[i][0], i};

        Arrays.sort(clone, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (clone[mid][0] >= intervals[i][1]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            ans[i] = clone[r][0] >= intervals[i][1] ? clone[r][1] : -1;
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/find-right-interval/solution/by-ac_oier-sijp/
    public int[] findRightInterval2(int[][] intervals) {

        int n = intervals.length;
        int[][] ss = new int[n][2];
        int[][] es = new int[n][2];
        for (int i = 0; i < n; i++) {
            ss[i] = new int[]{intervals[i][0], i};
            es[i] = new int[]{intervals[i][1], i};
        }

        Arrays.sort(ss, (a, b) -> a[0] - b[0]);
        Arrays.sort(es, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];

        for (int i = 0, j = 0; i < n; i++) {

            int[] cur = es[i];
            int loc = cur[0];
            int idx = cur[1];

            while (j < n && ss[j][0] < loc)
                j++;

            ans[idx] = j == n ? -1 : ss[j][1];
        }

        return ans;
    }
}
