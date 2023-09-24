package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-11 23:00
 */
public class Number1035 {

    // 参考：https://leetcode.cn/problems/uncrossed-lines/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-bkaas/
    public int maxUncrossedLines(int[] nums1, int[] nums2) {

        int n = nums1.length;
        int m = nums2.length;
        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (nums1[i - 1] == nums2[j - 1])
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
            }
        }

        return f[n][m];
    }
}
