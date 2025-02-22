package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2025/2/22 10:22
 * @Author: BobKing
 * @Description:
 */
public class Number2506 {

    public int similarPairs(String[] words) {

        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        int ans = 0;

        for (String s : words) {
            int mask = 0;
            for (char c : s.toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            int c = cnt.getOrDefault(mask, 0);
            ans += c;
            cnt.put(mask, c + 1);
        }
        return ans;
    }

}
