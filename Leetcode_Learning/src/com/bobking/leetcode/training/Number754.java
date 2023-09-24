package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-04 19:54
 */
public class Number754 {

    // 参考：https://leetcode.cn/problems/reach-a-number/solution/by-ac_oier-o4ze/
    public int reachNumber(int target) {

        if (target < 0)
            target = -target;

        int k = (int) Math.sqrt(2 * target);
        int dist = k * (k + 1) / 2;

        while (dist < target || (dist - target) % 2 == 1) {
            k++;
            dist = k * (k + 1) / 2;
        }
        return k;
    }
}
