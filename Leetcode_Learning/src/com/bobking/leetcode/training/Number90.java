package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-09-11 8:34
 */
public class Number90 {

    // 参考：https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/
    public List<List<Integer>> subsetsWithDup1(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // 因为结果中不能有重复元素，所以需要先排序
        // 这里没有用 Hashset 将结果进行去重
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[] nums, int start, ArrayList<Integer> list, List<List<Integer>> res) {

        res.add(new ArrayList<Integer>(list));
        for (int i = start; i < nums.length; i++) {
            // 和上个数字相等就跳过
            if (i > start && nums[i] == nums[i - 1])
                continue;

            list.add(nums[i]);
            dfs(nums, i + 1, list, res);
            // 回溯
            list.remove(list.size() - 1);
        }
    }

    // 参考：https://leetcode-cn.com/problems/subsets-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-19/
    public List<List<Integer>> subsetsWithDup2(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        Arrays.sort(nums);

        // 保存新解的开始位置
        int start = 1;
        for (int i = 0; i < nums.length; i++) {

            List<List<Integer>> resTmp = new ArrayList<>();
            // 遍历之前的所有结果
            for (int j = 0; j < res.size(); j++) {
                List<Integer> list = res.get(j);
                // 不能简单地像 dfs 中那样遇到重复的就跳过这个数字
                // 如果出现重复数字，就跳过所有******旧解*******
                //                                注意 j < start 这个判断
                if (i > 0 && nums[i] == nums[i - 1] && j < start)
                    continue;

                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[i]);
                resTmp.add(tmp);
            }

            start = res.size();
            res.addAll(resTmp);
        }

        return res;
    }


}
