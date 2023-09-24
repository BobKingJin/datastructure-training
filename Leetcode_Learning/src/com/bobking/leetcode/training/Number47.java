package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2021-07-22 23:48
 */
public class Number47 {

    // 参考：https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return res;

        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);
        // 利用 boolean 类型数组去重
        boolean[] used = new boolean[nums.length];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<Integer>(nums.length);
        dfs(nums, nums.length, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int index, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {

        if (index == len) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        // 因为顺序是任意的，所以每次都是从角标 0 开始
        // 例如 1 1 2，当第一个数为 2 时，这时前面两个 1 还是可以用的
        for (int i = 0; i < len; i++) {

            if (used[i])
                continue;

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // !used[i - 1]这个位置注意
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                continue;

            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, index + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }
}
