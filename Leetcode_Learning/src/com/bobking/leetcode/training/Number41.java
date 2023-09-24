package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Number41 {

    // 参考：程序猿代码指南P415
    public int firstMissingPositive1(int[] nums) {

        if (nums == null || nums.length == 0)
            return 1;

        int l = 0;
        int r = nums.length;

        while (l < r) {

            if (nums[l] == l + 1) {
                l++;
                //                                   nums[nums[l] - 1] == nums[l] 表示重复了
            } else if (nums[l] <= l || nums[l] > r || (nums[nums[l] - 1] == nums[l])) {
                // 将 nums 最后位置的数(nums[r - 1])放到位置 l 上
                nums[l] = nums[--r];
            } else {
                swap(nums, l, nums[l] - 1);
            }
        }

        return l + 1;
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 参考：https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
    public int firstMissingPositive2(int[] nums) {

        if (nums == null || nums.length == 0)
            return 1;

        Set<Integer> hashSet = new HashSet<Integer>();
        for (int num : nums)
            hashSet.add(num);

        for (int i = 1; i <= nums.length; i++) {
            if (!hashSet.contains(i))
                return i;
        }

        return nums.length + 1;
    }

}
