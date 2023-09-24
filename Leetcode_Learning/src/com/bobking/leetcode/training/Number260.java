package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-06-27 12:25
 */
public class Number260 {

    // 参考：程序猿代码指南P358
    public int[] singleNumbers(int[] nums) {

        if (nums == null || nums.length == 0)
            return nums;

        int[] res = new int[2];

        int multi1 = 0;
        for (int num : nums)
            multi1 ^= num;

        // 找到 multi 中从右往左的第一个为 1 的位数
        int righOne = multi1 & (~multi1 + 1);

        int multi2 = 0;
        for (int num : nums) {
            if ((num & righOne) != 0)
                multi2 ^= num;
        }

        res[0] = multi2;
        res[1] = multi2 ^ multi1;

        return res;
    }
}
