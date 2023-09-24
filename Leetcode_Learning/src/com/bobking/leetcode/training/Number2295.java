package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-06-25 7:34
 */
public class Number2295 {

    // 参考：https://leetcode.cn/problems/replace-elements-in-an-array/solution/naojin-by-endlesscheng-aq0n/
    public int[] arrayChange1(int[] nums, int[][] operations) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);
        for (int i = 0; i < nums.length; i++)
            map.put(nums[i], i);

        for (int[] op : operations) {
            int x = op[0];
            int y = op[1];
            int index = map.get(x);
            nums[index] = y;
            map.remove(x);
            map.put(y, index);
        }

        return nums;
    }

    public int[] arrayChange2(int[] nums, int[][] operations) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);

        for (int i = operations.length - 1; i >= 0; i--) {
            int[] p = operations[i];
            int x = p[0];
            int y = p[1];
            if (map.containsKey(y))
                y = map.get(y);
            map.put(x, y);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                nums[i] = map.get(nums[i]);
        }

        return nums;
    }


}
