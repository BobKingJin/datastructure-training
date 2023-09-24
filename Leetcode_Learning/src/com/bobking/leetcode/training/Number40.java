package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2021-07-22 23:38
 */
public class Number40 {

    // 参考：https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0)
            return res;

        // 排序，方便后面剪枝
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<Integer>(candidates.length);
        dfs(candidates, candidates.length, 0, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int len, int begin, int target, Deque<Integer> path, List<List<Integer>> res) {

        if (target == 0) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 大剪枝
            if (candidates[i] > target)
                break;

            // 小剪枝：同一层相同数值的节点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过
            // 用 continue 即防止发生重复
            // 例如 1  1  4 taget = 5，那么结果只有 1 4，不能重复
            if (i > begin && candidates[i] == candidates[i - 1])
                continue;

            path.addLast(candidates[i]);
            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            dfs(candidates, len, i + 1, target - candidates[i], path, res);
            // 回溯
            path.removeLast();
        }
    }

}
