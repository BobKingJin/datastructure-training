package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number39 {

    // 参考：https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length < 1 || target < 1) {
            return res;
        }

        backTrace1(candidates, 0, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    private void backTrace1(int[] candidates, int index, int sum, int target, List<Integer> list,
        List<List<Integer>> res) {

        // 递归结束条件
        if (sum > target) {
            return;
        }

        if (sum == target) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            // 因为同一个数可以重复取，所以 index 角标没变
            backTrace1(candidates, i, sum + candidates[i], target, list, res);
            // 回溯
            list.remove(list.size() - 1);
        }
    }

    // 参考：https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length < 1 || target < 1) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        backTrace2(candidates, 0, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    private void backTrace2(int[] candidates, int index, int sum, int target, List<Integer> list,
        List<List<Integer>> res) {

        if (sum == target) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序
            if (sum + candidates[i] > target) {
                break;
            }
            list.add(candidates[i]);
            // candidates中的元素可以重复使用，所以这里的 index = i 不变
            backTrace2(candidates, i, sum + candidates[i], target, list, res);
            list.remove(list.size() - 1);
        }
    }
}
