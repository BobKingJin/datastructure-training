package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-05-23 7:58
 */
public class Number1207 {

    public boolean uniqueOccurrences1(int[] arr) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++)
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

        return map.size() == new HashSet<Integer>(map.values()).size();
    }

    public boolean uniqueOccurrences2(int[] arr) {

        int[] count = new int[2001];
        for (int i = 0; i < arr.length; i++)
            count[1000 + arr[i]]++;

        Set<Integer> set = new HashSet<Integer>();
        for (int value : count) {
            if (value == 0)
                continue;
            if (!set.add(value))
                return false;
        }
        return true;
    }
}
