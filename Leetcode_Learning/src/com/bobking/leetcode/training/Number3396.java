package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2025/5/24 11:21
 * @Author: BobKing
 * @Description:
 */
public class Number3396 {

    // 参考: https://leetcode.cn/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/solutions/3027035/on-yi-ci-bian-li-jian-ji-xie-fa-pythonja-jgox/?envType=daily-question&envId=2025-05-24
    public int minimumOperations(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (!seen.add(nums[i])) {
                return i / 3 + 1;
            }
        }
        return 0;
    }

}
