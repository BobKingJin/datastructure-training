package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Number491 {

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> findSubsequences(int[] nums) {

        // idx 初始化为 -1
        dfs(nums, -1, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int[] nums, int idx, List<Integer> curList) {

        if (curList.size() > 1)
            res.add(new ArrayList<Integer>(curList));

        // 借助 set 对 [idx + 1, nums.length - 1] 范围内的数去重
        Set<Integer> set = new HashSet<Integer>();

        for (int i = idx + 1; i < nums.length; i++) {

            // 1. 如果 set 中已经有与 nums[i] 相同的值了，说明加上 nums[i] 后的所有可能的递增序列之前已经被搜过一遍了，因此停止继续搜索
            if (set.contains(nums[i]))
                continue;

            set.add(nums[i]);

            if (idx == -1 || nums[i] >= nums[idx]) {
                curList.add(nums[i]);
                // 注意这个位置角标没有改变
                dfs(nums, i, curList);
                // 回溯
                curList.remove(curList.size() - 1);
            }
        }
    }
}
