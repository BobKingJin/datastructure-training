package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-10 14:21
 */
public class Number801 {

    // 思路参考：https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/solution/leetcode-801-wo-gan-jio-ying-gai-jiang-de-hen-tou-/
    // 代码参考：https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/solution/by-ac_oier-fjhp/
    public int minSwap(int[] nums1, int[] nums2) {

        int n = nums1.length;
        // 定义 f[i][j] 为考虑下标范围为 [0, i] 的元素，且位置 i 的交换状态为 j 时
        // （其中 j = 0 为不交换，j = 1 为交换）两数组满足严格递增的最小交换次数
        int[][] f = new int[n][2];

        for (int i = 1; i < n; i++)
            f[i][0] = f[i][1] = n + 10;

        f[0][1] = 1;

        for (int i = 1; i < n; i++) {

            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                f[i][0] = f[i - 1][0];
                f[i][1] = f[i - 1][1] + 1;
            }

            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                f[i][0] = Math.min(f[i][0], f[i - 1][1]);
                f[i][1] = Math.min(f[i][1], f[i - 1][0] + 1);
            }
        }
        return Math.min(f[n - 1][0], f[n - 1][1]);
    }
}
