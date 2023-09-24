package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number18 {

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    // 参考：程序猿代码指南P380
    // 对比Number15
    public List<List<Integer>> fourSum1(int[] nums, int target) {

        if (nums == null || nums.length < 1)
            return res;

        // 先进行排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            // 去重
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    // 去重
                    if (j == i + 1 || nums[j] != nums[j - 1])
                        getUnique(nums, i, j, j + 1, nums.length - 1, target - nums[i] - nums[j]);
                }
            }
        }

        return res;
    }

    private void getUnique(int[] nums, int i, int j, int l, int r, int target) {

        while (l < r) {
            if (nums[l] + nums[r] < target) {
                l++;
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                // 去重
                if (l == j + 1 || nums[l] != nums[l - 1]) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                }
                l++;
                r--;
            }
        }
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length < 1)
            return res;

        Arrays.sort(nums);

        for (int first = 0; first < nums.length; first++) {

            // 去重
            if (first > 0 && nums[first] == nums[first - 1])
                continue;

            for (int second = first + 1; second < nums.length; second++) {

                if (second > first + 1 && nums[second] == nums[second - 1])
                    continue;

                int four = nums.length - 1;
                int targetCount = target - nums[first] - nums[second];

                for (int three = second + 1; three < nums.length; three++) {

                    if (three > second + 1 && nums[three] == nums[three - 1])
                        continue;

                    while (three < four && nums[three] + nums[four] > targetCount)
                        four--;

                    if (three == four)
                        break;

                    if (nums[three] + nums[four] == targetCount) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[three]);
                        list.add(nums[four]);
                        res.add(list);
                    }
                }
            }
        }

        return res;
    }
}
