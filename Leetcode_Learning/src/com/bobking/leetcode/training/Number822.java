package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-10-08 7:17
 */
public class Number822 {

    // 参考: https://leetcode.cn/problems/card-flipping-game/solutions/2368863/yue-du-li-jie-ti-pythonjavacgojs-by-endl-ze7f/?envType=daily-question&envId=2023-10-08
    public int flipgame(int[] fronts, int[] backs) {

        HashSet<Integer> forbidden = new HashSet<Integer>();
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i] == backs[i])
                forbidden.add(fronts[i]);
        }

        int ans = Integer.MAX_VALUE;

        for (int x : fronts) {
            if (!forbidden.contains(x))
                ans = Math.min(ans, x);
        }

        for (int x : backs) {
            if (!forbidden.contains(x))
                ans = Math.min(ans, x);
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
