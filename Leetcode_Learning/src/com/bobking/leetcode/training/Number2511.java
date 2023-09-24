package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-18 7:10
 */
public class Number2511 {

    public int captureForts(int[] forts) {

        // 当前非零元素下标为 i，上一个非零元素下标为 pre
        int ans = 0;
        // 表示不存在
        int pre = -1;

        for (int i = 0; i < forts.length; i++) {
            if (forts[i] != 0) {
                // 一个是 1，另一个是 -1
                if (pre >= 0 && forts[i] != forts[pre])
                    ans = Math.max(ans, i - pre - 1);
                pre = i;
            }
        }
        return ans;
    }
}
