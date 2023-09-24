package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-31 6:27
 */
public class Number2672 {

    public int[] colorTheArray(int n, int[][] queries) {

        int q = queries.length;
        int cnt = 0;
        int[] ans = new int[q];
        // 避免讨论下标出界的情况
        int[] a = new int[n + 2];

        for (int qi = 0; qi < q; qi++) {
            // 下标改成从 1 开始
            int i = queries[qi][0] + 1;
            int c = queries[qi][1];
            if (a[i] > 0)
                cnt -= (a[i] == a[i - 1] ? 1 : 0) + (a[i] == a[i + 1] ? 1 : 0);
            a[i] = c;
            cnt += (a[i] == a[i - 1] ? 1 : 0) + (a[i] == a[i + 1] ? 1 : 0);
            ans[qi] = cnt;
        }
        return ans;
    }
}
