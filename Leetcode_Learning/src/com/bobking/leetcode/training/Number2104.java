package com.bobking.leetcode.training;

public class Number2104 {

    public long subArrayRanges1(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        long res = 0L;

        for (int i = 0; i < nums.length - 1; i++) {
            // min 和 max 分别为子数组中的最小值和最大值
            int min = nums[i];
            int max = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < min)
                    min = nums[j];
                if (nums[j] > max)
                    max = nums[j];

                res += max - min;
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/sum-of-subarray-ranges/solution/gong-shui-san-xie-yi-ti-san-jie-qu-jian-wn84z/
    public long subArrayRanges2(int[] nums) {

        if(nums == null || nums.length == 0)
            return 0;

        int n = nums.length;
        // f[l][r][k] 为区间 [l, r] 范围内的最值情况
        // 其中 k 非 0 即 1
        // f[l][r][0] 代表区间 [l, r] 内的最小值
        // f[l][r][1] 代表区间 [l, r] 内的最大值
        // f[l][r][0] = min(f[l][r − 1][0], nums[r])
        // f[l][r][1]= max(f[l][r − 1][1], nums[r])

        int[][][] f = new int[n][n][2];

        for (int i = 0; i < n; i++) {
            f[i][i][0] = nums[i];
            f[i][i][1] = nums[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                f[l][r][0] = Math.min(nums[r], f[l][r - 1][0]);
                f[l][r][1] = Math.max(nums[r], f[l][r - 1][1]);
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++)
                ans += f[i][j][1] - f[i][j][0];
        }

        return ans;
    }

}
