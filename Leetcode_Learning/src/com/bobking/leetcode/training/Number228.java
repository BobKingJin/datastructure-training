package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-07-17 11:54
 */
public class Number228 {

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // j 向后遍历，直到不满足连续递增(即 nums[j] + 1 != nums[j + 1])
            // 或者 j 达到数组边界，则当前连续递增区间 [i, j] 遍历完毕
            if (j == nums.length - 1 || nums[j] + 1 != nums[j + 1]) {
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if (i != j) {
                    sb.append("->").append(nums[j]);
                }
                res.add(sb.toString());
                i = j + 1;
            }
        }
        return res;
    }
}
