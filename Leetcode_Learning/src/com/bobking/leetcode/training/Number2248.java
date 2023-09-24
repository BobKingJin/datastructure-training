package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-06-19 9:40
 */
public class Number2248 {

    public List<Integer> intersection(int[][] nums) {

        List<Integer> res = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int[] num : nums){
            for(int n : num)
                map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == nums.length)
                res.add(entry.getKey());
        }

        Collections.sort(res);

        return res;
    }
}
