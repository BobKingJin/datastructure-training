package com.bobking.leetcode.training;

/**
 * @Date: 2025/9/7 12:02
 * @Author: BobKing
 * @Description:
 */
public class Number3355 {

    // 参考: https://leetcode.cn/problems/zero-array-transformation-i/solutions/2991455/mo-ban-chai-fen-shu-zu-pythonjavacgo-by-i4axs/?envType=daily-question&envId=2025-09-07
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int[] q : queries) {
            diff[q[0]]++;
            diff[q[1] + 1]--;
        }

        int sumD = 0;
        for (int i = 0; i < n; i++) {
            sumD += diff[i];
            // nums[i] 无法变成 0
            if (nums[i] > sumD) {
                return false;
            }
        }
        return true;
    }

}
