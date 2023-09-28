package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Number128 {

    // 参考：程序猿代码指南P248
    public int longestConsecutive1(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int max = 1;
        // key：数组中的已经遍历过的数  value：该数所在的最长连续序列的长度
        // map 中只更新连续序列中的最大值和最小值
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
                if (map.containsKey(nums[i] - 1))
                    max = Math.max(max, merge(map, nums[i] - 1, nums[i]));
                if (map.containsKey(nums[i] + 1))
                    max = Math.max(max, merge(map, nums[i], nums[i] + 1));
            }
        }

        return max;
    }

    private int merge(HashMap<Integer, Integer> map, int less, int more) {

        int left = less - map.get(less) + 1;
        int right = more + map.get(more) - 1;
        int length = right - left + 1;
        map.put(left, length);
        map.put(right, length);
        return length;
    }

    // 参考：https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
    public int longestConsecutive2(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int longestStreak = 0;

        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums)
            set.add(num);

        for (int num : set) {
            int currentNum = num;
            int currentStreak = 1;
            // 相当于找到某个连续序列中的最小值
            // 已知有一个 x, x + 1, x + 2, ⋯, x + y 的连续序列
            // 是没有必要重新去统计 x + 1, x + 2, ⋯, x + y的连续序列的，直接跳过即可
            if (!set.contains(currentNum - 1)) {
                while (set.contains(currentNum + 1)) {
                    currentStreak++;
                    currentNum++;
                }
            }
            longestStreak = Math.max(longestStreak, currentStreak);
        }

        return longestStreak;
    }
}
