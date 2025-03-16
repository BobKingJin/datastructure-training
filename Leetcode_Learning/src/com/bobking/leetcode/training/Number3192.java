package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/16 11:17
 * @Author: BobKing
 * @Description:
 */
public class Number3192 {

    // 参考: https://leetcode.cn/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-ii/solutions/2819086/cong-zuo-dao-you-cao-zuo-jian-ji-xie-fa-yzcde/?envType=daily-question&envId=2025-03-16
    public int minOperations(int[] nums) {
        int k = 0;
        for (int x : nums) {
            // 必须操作
            if (x == k % 2) {
                k++;
            }
        }
        return k;
    }

}
