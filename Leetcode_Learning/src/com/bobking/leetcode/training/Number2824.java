package com.bobking.leetcode.training;

import java.util.Collections;
import java.util.List;

/**
 * @Date: 2023/12/17 7:40
 * @Author: BobKing
 * @Description:
 */
public class Number2824 {

    // 参考: https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target/solutions/2396216/onlogn-pai-xu-shuang-zhi-zhen-by-endless-qk40/?envType=daily-question&envId=2023-12-17
    public int countPairs(List<Integer> nums, int target) {

        Collections.sort(nums);

        int ans = 0;
        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            if (nums.get(left) + nums.get(right) < target) {
                ans += right - left;
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}
