package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-08-12 23:40
 */
public class Number442 {

    // 参考：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/solution/chou-ti-yuan-li-ji-yu-yi-huo-yun-suan-jiao-huan-li/
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> res = new ArrayList<Integer>();

        if (nums == null || nums.length == 0)
            return res;
        // 因为 1 <= nums[i] <= nums.length
        // 让数值 1 就放在索引位置 0 处
        // 让数值 2 就放在索引位置 1 处
        // 让数值 3 就放在索引位置 2 处.........
        for (int i = 0; i < nums.length; i++) {
            // 注意这里是while循环
            while (nums[nums[i] - 1] != nums[i])
                // 这里交换传的是角标
                swap(nums, i, nums[i] - 1);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i)
                res.add(nums[i]);
        }

        return res;
    }

    private void swap(int[] nums, int index1, int index2) {

        if (index1 == index2)
            return;

        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

}
