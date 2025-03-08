package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/8 15:38
 * @Author: BobKing
 * @Description:
 */
public class Number3158 {

    public int duplicateNumbersXOR(int[] nums) {
        int ans = 0;
        long vis = 0;

        for (int x : nums) {
            // x 在 vis 中
            if ((vis >> x & 1) > 0) {
                ans ^= x;
            } else { // 把 x 加到 vis 中
                vis |= 1L << x;
            }
        }
        return ans;
    }

}
