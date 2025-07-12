package com.bobking.leetcode.training;

import java.util.List;

/**
 * @Date: 2025/7/12 13:54
 * @Author: BobKing
 * @Description:
 */
public class Number624 {

    // 参考: https://leetcode.cn/problems/maximum-distance-in-arrays/solutions/3067679/mei-ju-you-wei-hu-zuo-pythonjavaccgojsru-wtgb/?envType=daily-question&envId=2025-07-12
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        // 防止减法溢出
        int mn = Integer.MAX_VALUE / 2;
        int mx = Integer.MIN_VALUE / 2;
        for (List<Integer> a : arrays) {
            int x = a.get(0);
            int y = a.get(a.size() - 1);
            ans = Math.max(ans, Math.max(y - mn, mx - x));
            mn = Math.min(mn, x);
            mx = Math.max(mx, y);
        }
        return ans;
    }

}
