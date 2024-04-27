package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-04-03 22:44
 */
public class Number149 {

    // 参考：https://leetcode-cn.com/problems/max-points-on-a-line/solution/gong-shui-san-xie-liang-chong-mei-ju-zhi-u44s/
    public int maxPoints1(int[][] ps) {
        
        int n = ps.length;
        int res = 1;
        
        for (int i = 0; i < n; i++) {
            int[] x = ps[i];
            for (int j = i + 1; j < n; j++) {
                int[] y = ps[j];
                int count = 2;
                for (int k = j + 1; k < n; k++) {
                    int[] p = ps[k];
                    int s1 = (y[1] - x[1]) * (p[0] - y[0]);
                    int s2 = (p[1] - y[1]) * (y[0] - x[0]);
                    if (s1 == s2) 
                        count++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    // 参考：https://leetcode-cn.com/problems/max-points-on-a-line/solution/gong-shui-san-xie-liang-chong-mei-ju-zhi-u44s/
    public int maxPoints2(int[][] ps) {
        
        int n = ps.length;
        int res = 1;
        
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            // 由当前点 i 发出的直线所经过的最多点数量
            int max = 0;
            for (int j = i + 1; j < n; j++) {
                int x1 = ps[i][0];
                int y1 = ps[i][1];
                int x2 = ps[j][0];
                int y2 = ps[j][1];
                int a = x1 - x2;
                int b = y1 - y2;
                int k = gcd(a, b);
                String key = (a / k) + "_" + (b / k);
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            res = Math.max(res, max + 1);
        }
        return res;
    }
    
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
