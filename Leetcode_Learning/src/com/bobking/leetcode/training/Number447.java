package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-10-14 8:51
 */
public class Number447 {

    public int numberOfBoomerangs(int[][] points) {

        int n = points.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                int dist = x * x + y * y;
                map.put(dist, map.getOrDefault(dist, 0) + 1);
            }

            for (int dist : map.keySet()) {
                int cnt = map.get(dist);
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }
}
