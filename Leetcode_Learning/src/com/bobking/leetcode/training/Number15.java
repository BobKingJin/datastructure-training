package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number15 {

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    // 参考：程序猿代码指南P380
    // 对比Number15
    public List<List<Integer>> threeSum1(int[] nums) {

        if (nums == null || nums.length < 1)
            return res;

        // 先进行排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i == 0 || nums[i] != nums[i - 1])
                getUnique(nums, i, i + 1, nums.length - 1, -nums[i]);
        }
        return res;
    }

    private void getUnique(int[] nums, int f, int l, int r, int target) {

        while (l < r) {
            if (nums[l] + nums[r] < target) {
                l++;
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                // 去重
                if (l == f + 1 || nums[l] != nums[l - 1]) {
                    List<Integer> third = new ArrayList<Integer>();
                    third.add(nums[f]);
                    third.add(nums[l]);
                    third.add(nums[r]);
                    res.add(third);
                }
                l++;
                r--;
            }
        }
    }

    // 同方法一
    // 参考：https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
    public List<List<Integer>> threeSum2(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length < 3)
            return res;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字大于 0，则三数之和一定大于 0，所以结束循环
            // 因为先对 nums 进行了排序，所以当 nums[i] > 0 时直接跳出循环
            if (nums[i] > 0)
                break;

            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int L = i + 1;
            int R = nums.length - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 去重
                    while (L < R && nums[L] == nums[L + 1])
                        L++;
                    // 去重
                    while (L < R && nums[R] == nums[R - 1])
                        R--;
                    L++;
                    R--;
                } else if (sum < 0){
                    L++;
                } else if (sum > 0){
                    R--;
                }
            }
        }

        return res;
    }

    public List<List<Integer>> threeSum3(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length < 1)
            return res;

        List<Integer> list = new ArrayList<Integer>();
        // 不排序, 进行递归
        backTrace(nums, 0, 0, list, res);
        return res;
    }

    private void backTrace(int[] nums, int index, int count, List<Integer> list, List<List<Integer>> res) {

        if (count == 0 && list.size() == 3) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        // list.size() == 3 时提前进行剪枝
        if (index == nums.length || list.size() == 3)
            return;

        // 以每个角标为起点
        for (int i = index; i < nums.length; i++) {
            count += nums[i];
            list.add(nums[i]);
            // (index + 1) 即表示不会重复取同一个角标上面的数
            backTrace(nums, i + 1, count, list, res);
            // 回溯
            list.remove(list.size() - 1);
            count -= nums[i];
        }
    }


}
