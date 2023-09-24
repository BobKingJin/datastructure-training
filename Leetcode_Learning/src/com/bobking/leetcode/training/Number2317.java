package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-07-02 8:12
 */
public class Number2317 {

    // 参考：https://leetcode.cn/problems/maximum-xor-after-operations/solution/yi-bu-bu-tis-by-endlesscheng-89kw/
    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int num : nums)
            ans |= num;
        return ans;
    }
}
