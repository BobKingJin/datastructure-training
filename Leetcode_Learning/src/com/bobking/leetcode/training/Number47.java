package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-07-22 23:48
 */
public class Number47 {

    // 参考：https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<Integer>(nums.length);
        dfs(nums, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int index, boolean[] used, Deque<Integer> path,
        List<List<Integer>> res) {

        if (index == nums.length) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        // 因为顺序是任意的，所以每次都是从角标 0 开始
        // 例如 1 1 2，当第一个数为 2 时，这时前面两个 1 还是可以用的
        for (int i = 0; i < nums.length; i++) {

            if (used[i]) {
                continue;
            }

            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // !used[i - 1]这个位置注意
            // used[i - 1] = false 表示该角标已经被遍历过, 例如: 1 1 2, 遍历到角标 1 的位置, used[0] = false
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, index + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }
}
