package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Interview08_04 {

    // 参考：https://leetcode-cn.com/problems/power-set-lcci/solution/hui-su-wei-yun-suan-deng-gong-4chong-fang-shi-jie-/
    public List<List<Integer>> subsets1(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0)
            return res;

        // 先添加一个空的集合
        res.add(new ArrayList<Integer>());
        // bfs
        for (int num : nums) {
            // 当遍历到 num 时(角标为 i)，那么以 nums[0] - nums[i - 1] 为结尾的子集都已遍历完
            for (int i = 0; i < res.size(); i++) {
                // 注意这里是每次新创建一个 list
                // 例如：现在 res 中有 [] [1] 遍历到 2
                // 那么[] [1]是保持不变的，添加的是 [2] [1 2]
                List<Integer> list = new ArrayList<Integer>(res.get(i));
                list.add(num);
                res.add(list);
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/power-set-lcci/solution/hui-su-wei-yun-suan-deng-gong-4chong-fang-shi-jie-/
    public List<List<Integer>> subsets2(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (nums == null || nums.length == 0)
            return res;

        backtrack(0, nums, new ArrayList<Integer>(), res);
        return res;
    }

    private void backtrack(int start, int[] nums, List<Integer> tempList, List<List<Integer>> res) {

        res.add(new ArrayList<Integer>(tempList));
        // 即以 nums 中的每个数字开头，进行递归，并且每次递归结束条件是 start == nums.length - 1
        for (int i = start; i < nums.length; i++) {

            tempList.add(nums[i]);
            // 递归
            // 注意不能包含重复元素，所有这里的索引必须每次进行 i + 1
            backtrack(i + 1, nums, tempList, res);
            // 回溯
            tempList.remove(tempList.size() - 1);
        }
    }

}
