package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-03-22 23:22
 */
public class Number1626 {

    // 参考：https://leetcode.cn/problems/best-team-with-no-conflicts/solution/zui-chang-di-zeng-zi-xu-lie-cong-on2-dao-ojqu/
    public int bestTeamScore(int[] scores, int[] ages) {

        int n = scores.length;
        int ans = 0;
        Integer[] ids = new Integer[n];

        for (int i = 0; i < n; ++i)
            ids[i] = i;

        // 分数从小到大排序，分数相同的按照年龄从小到大排序。
        Arrays.sort(ids, (i, j) -> scores[i] != scores[j] ? scores[i] - scores[j] : ages[i] - ages[j]);

        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (ages[ids[j]] <= ages[ids[i]])
                    f[i] = Math.max(f[i], f[j]);
            }
            f[i] += scores[ids[i]];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
