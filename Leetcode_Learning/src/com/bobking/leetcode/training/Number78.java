package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2020-11-23 21:49
 */
public class Number78 {

    // 参考：https://leetcode-cn.com/problems/subsets/solution/hui-su-suan-fa-by-powcai-5/
    public List<List<Integer>> subsets1(int[] nums) {

        if (nums == null || nums.length == 0)
            return null;

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // dfs
        backTrack(0, nums, res, new ArrayList<Integer>());
        return res;
    }

    private void backTrack(int index, int[] nums, List<List<Integer>> res, List<Integer> tmp) {

        // 1.因为数组 nums 有限，所以不会存在递归死循环的问题
        // 2.这里需要注意一点 res.add(new ArrayList<Integer>(tmp))，即每次都是重新 new ArrayList(tmp)
        //   即都是新建一个 List，然后加入结果集中
        res.add(new ArrayList<Integer>(tmp));
        for (int j = index; j < nums.length; j++) {
            tmp.add(nums[j]);
            // 从 j + 1角标开始向后添加进 tmp 中
            backTrack(j + 1, nums, res, tmp);
            // 回溯
            tmp.remove(tmp.size() - 1);
        }
    }

    // 参考：https://leetcode-cn.com/problems/subsets/solution/hui-su-si-xiang-tuan-mie-pai-lie-zu-he-zi-ji-wen-t/
    public List<List<Integer>> subsets2(int[] nums) {

        if (nums == null || nums.length == 0)
            return null;

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());

        // bfs
        for (int i = 0; i < nums.length; i++) {
            // 将 nums[i] 添加进 res 中的所有子集
            // 例如：1 2 3 4 5 当 i = 3 即 nums[3] = 4 时，此时 res 中应包含了 1 - 3 的所有子集
            // 此时将 4 添加进所有子集
            for (int j = 0; j < res.size(); j++) {
                List<Integer> list = new ArrayList<Integer>(res.get(j));
                list.add(nums[i]);
                // 每次都将 list 添加进 res 中
                res.add(list);
            }
        }

        return res;
    }
}
