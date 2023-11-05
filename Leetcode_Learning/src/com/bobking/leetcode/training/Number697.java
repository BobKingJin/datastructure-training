package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-11-03 8:18
 */
public class Number697 {

    public int findShortestSubArray(int[] nums) {

        // 定义一个Map, Key为数组中的值, Value为一个数组
        // 数组的第一个元素为 Key 出现的次数, 第二个元素为 Key 第一次出现的位置, 第三个元素为 Key 最后一次出现的位置
        Map<Integer, int[]> map = new HashMap<Integer, int[]>();
        int n = nums.length;
        // 遍历数组, 将每一个元素出现的次数、第一次出现的位置、最后一次出现的位置记录下来
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }

        int maxNum = 0;
        int minLen = 0;

        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] temp = entry.getValue();
            if (temp[0] > maxNum) {
                maxNum = temp[0];
                minLen = temp[2] - temp[1] + 1;
            } else if (temp[0] == maxNum) {
                if (minLen > temp[2] - temp[1] + 1)
                    minLen = temp[2] - temp[1] + 1;
            }
        }
        return minLen;
    }
}
