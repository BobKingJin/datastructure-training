package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-16 7:09
 */
public class Number330 {

    // 参考：https://leetcode.cn/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-by-leetcode-solu-klp1/
    public int minPatches(int[] nums, int n) {

        int patches = 0;
        long x = 1;
        int length = nums.length;
        int index = 0;

        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }
}
