package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-08 23:36
 */
public class Number27 {

    // 参考：https://leetcode-cn.com/problems/remove-element/solution/hua-jie-suan-fa-27-yi-chu-yuan-su-by-guanpengchn/
    public int removeElement1(int[] nums, int val) {

        // res 既充当角标，又充当返回结果
        int res = 0;

        for(int num : nums) {
            if(num != val)
                nums[res++] = num;
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/remove-element/solution/hua-jie-suan-fa-27-yi-chu-yuan-su-by-guanpengchn/
    public int removeElement2(int[] nums, int val) {

        int res = nums.length;

        for (int i = 0; i < res; ) {
            // 此时不改变角标(即不执行 i++)，而是把 res - 1 位置的元素交换到 i 位置
            if (nums[i] == val) {
                nums[i] = nums[res - 1];
                res--;
            } else {
                i++;
            }
        }

        return res;
    }

}
