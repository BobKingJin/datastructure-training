package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number1 {

    // 先排序然后用双指针，存在一个问题，那就是
    // int[] temp = new int[nums.length] 然后将 nums 中的数拷贝进 temp
    // 然后对 temp 进行排序，用双指针去求 target ，但是不知道此时两个数在原 nums 数组中的位置

    // 参考：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
    public int[] twoSum1(int[] nums, int target) {

        if (nums == null || nums.length < 1) {
            return nums;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    // 参考：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-solution/
    public int[] twoSum2(int[] nums, int target) {

        if (nums == null || nums.length < 1) {
            return nums;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }


}
