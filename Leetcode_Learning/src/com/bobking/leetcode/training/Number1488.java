package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author BobKing
 * @create 2023-10-13 8:51
 */
public class Number1488 {

    // 参考: https://leetcode.cn/problems/avoid-flood-in-the-city/solutions/298425/avoid-flood-in-the-city-by-ikaruga/?envType=daily-question&envId=2023-10-13
    public int[] avoidFlood(int[] rains) {

        int len = rains.length;
        int[] ans = new int[len];
        // 记录晴天的日期
        TreeSet<Integer> sunny = new TreeSet<Integer>();
        // 记录每个湖泊上一次下雨的日期
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        Arrays.fill(ans, 1);

        for (int i = 0; i < len; i++) {
            int isRain = rains[i];
            if (isRain == 0) {
                sunny.add(i);
                continue;
            } else {
                ans[i] = -1;
                // 如果之前下过雨
                if (map.containsKey(isRain)) {
                    Integer n = sunny.ceiling(map.get(isRain));
                    if (n == null)
                        return new int[0];
                    ans[n] = isRain;
                    sunny.remove(n);
                }
                map.put(isRain, i);
            }
        }
        return ans;
    }
}
