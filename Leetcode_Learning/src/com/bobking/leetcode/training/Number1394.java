package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 2025/7/5 11:53
 * @Author: BobKing
 * @Description:
 */
public class Number1394 {

    public int findLucky(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int x : arr) {
            cnt.merge(x, 1, Integer::sum);
        }

        int ans = -1;
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            int x = e.getKey();
            int c = e.getValue();
            if (x == c) {
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }

}
