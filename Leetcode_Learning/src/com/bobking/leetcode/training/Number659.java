package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-10-09 12:15
 */
public class Number659 {

    // 参考：https://leetcode.cn/problems/split-array-into-consecutive-subsequences/solution/tan-xin-suan-fa-jian-cha-shu-zu-neng-fou-bei-fen-w/
    public boolean isPossible(int[] nums) {

        // 用一个哈希表统计每个元素出现的次数
        Map<Integer, Integer> countNum = new HashMap<Integer, Integer>();
        for (int num : nums)
            countNum.put(num, countNum.getOrDefault(num, 0) + 1);

        // 定义一个哈希表记录最长的子序列
        Map<Integer, Integer> tail = new HashMap<Integer, Integer>();
        for (int num : nums) {

            int count = countNum.getOrDefault(num, 0);
            // 当前元素已经用完，直接跳过
            if (count <= 0) {
                continue;
                // 前面还有数字，可以构成以 num 结尾的子序列
            } else if (tail.getOrDefault(num - 1, 0) > 0) {
                countNum.put(num, count - 1);
                // 覆盖当前最长的子序列
                tail.put(num - 1, tail.get(num - 1) - 1);
                // 当前以 num 结尾的子序列 + 1
                tail.put(num, tail.getOrDefault(num, 0) + 1);
                // 前面无数字构成子序列后，判断能不能跟后面的构成子序列
            } else if (countNum.getOrDefault(num + 1, 0) > 0 && countNum.getOrDefault(num + 2, 0) > 0) {
                countNum.put(num, count - 1);
                countNum.put(num + 1, countNum.get(num + 1) - 1);
                countNum.put(num + 2, countNum.get(num + 2) - 1);
                // 当前以 num + 2 结尾的子序列 + 1
                tail.put(num + 2, tail.getOrDefault(num + 2, 0) + 1);
            } else {
                // 前后不能构成子序列直接返回 false
                return false;
            }
        }
        return true;
    }
}
