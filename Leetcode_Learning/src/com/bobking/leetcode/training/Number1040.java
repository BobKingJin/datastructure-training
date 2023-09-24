package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-07 7:54
 */
public class Number1040 {

    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int e1 = stones[n - 2] - stones[0] - n + 2;
        int e2 = stones[n - 1] - stones[1] - n + 2; // 计算空位
        int maxMove = Math.max(e1, e2);
        if (e1 == 0 || e2 == 0) // 特殊情况：没有空位
            return new int[]{Math.min(2, maxMove), maxMove};
        int maxCnt = 0, left = 0;
        for (int right = 0; right < n; ++right) { // 滑动窗口：枚举右端点
            while (stones[right] - stones[left] + 1 > n) // 窗口大小大于 n
                ++left;
            maxCnt = Math.max(maxCnt, right - left + 1); // 维护窗口内的最大石子数
        }
        return new int[]{n - maxCnt, maxMove};
    }
}
