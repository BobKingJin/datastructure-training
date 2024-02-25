package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2020-11-24 20:57
 */
public class Number46 {

    // 参考：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-by-powcai-2/
    // 对比Number17
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0)
            return res;

        List<Integer> list = new ArrayList<Integer>();
        // 已经访问过的，不允许重复访问
        boolean[] visited = new boolean[nums.length];
        backTrack(0, nums, visited, list, res);
        return res;
    }

    private void backTrack(int index, int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> res) {

        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        // 在递归的时候, 每次都是从头开始遍历的, 因此需要 bool 数组来进行去重
        // 因为例如：[1, 2, 3] 可以第一个为 2, 但是此时还是可以取到 1, 所以每次都是重头开始取值
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                backTrack(index + 1, nums, visited, list, res);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
