package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-02-10 23:27
 */
public class Number594 {

    // 参考：https://leetcode.cn/problems/longest-harmonious-subsequence/solution/gong-shui-san-xie-yi-ti-shuang-jie-hua-d-quuh/
    public int findLHS1(int[] nums) {

        Arrays.sort(nums);

        int n = nums.length;
        int ans = 0;

        for (int i = 0, j = 0; j < n; j++) {
            while (i < j && nums[j] - nums[i] > 1)
                i++;
            if (nums[j] - nums[i] == 1)
                ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/longest-harmonious-subsequence/solution/gong-shui-san-xie-yi-ti-shuang-jie-hua-d-quuh/
    public int findLHS2(int[] nums) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);
        int ans = 0;

        for (int i : nums) {
            if (map.containsKey(i - 1))
                ans = Math.max(ans, map.get(i) + map.get(i - 1));
        }
        return ans;
    }
}
