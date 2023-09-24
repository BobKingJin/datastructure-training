package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-04-30 15:36
 */
public class Number268 {

    // 参考：https://leetcode-cn.com/problems/missing-number/solution/gong-shui-san-xie-yi-ti-wu-jie-pai-xu-ji-te3s/
    public int missingNumber1(int[] nums) {

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i)
                return i;
        }

        return nums.length;
    }

    // 参考：https://leetcode-cn.com/problems/missing-number/solution/gong-shui-san-xie-yi-ti-wu-jie-pai-xu-ji-te3s/
    public int missingNumber2(int[] nums) {

        boolean[] hash = new boolean[nums.length + 1];

        for (int i = 0; i < nums.length; i++)
            hash[nums[i]] = true;

        for (int i = 0; i < nums.length; i++)
            if (!hash[i])
                return i;

        return nums.length;
    }

    // 参考：https://leetcode-cn.com/problems/missing-number/solution/gong-shui-san-xie-yi-ti-wu-jie-pai-xu-ji-te3s/
    public int missingNumber3(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++)
            if (nums[i] != i && nums[i] < n)
                swap(nums, nums[i], i--);

        for (int i = 0; i < n; i++)
            if (nums[i] != i)
                return i;

        return n;
    }

    private void swap(int[] nums, int i, int j) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }

}
