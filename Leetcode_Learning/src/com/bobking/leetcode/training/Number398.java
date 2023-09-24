package com.bobking.leetcode.training;

import java.util.*;

public class Number398 {

    // 参考：https://leetcode-cn.com/problems/random-pick-index/solution/by-ac_oier-zhml/
    private class Solution{

        Random random = new Random();
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        private Solution(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                List<Integer> list = map.getOrDefault(nums[i], new ArrayList<Integer>());
                list.add(i);
                map.put(nums[i], list);
            }
        }

        private int pick(int target) {
            List<Integer> list = map.get(target);
            return list.get(random.nextInt(list.size()));
        }
    }




}
