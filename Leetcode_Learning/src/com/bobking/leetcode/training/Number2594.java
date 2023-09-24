package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-18 8:34
 */
public class Number2594 {

    // 参考：https://leetcode.cn/problems/minimum-time-to-repair-cars/solutions/2177199/er-fen-da-an-pythonjavacgo-by-endlessche-keqf/?envType=daily-question&envId=2023-09-18
    public long repairCars(int[] ranks, int cars) {

        int minR = ranks[0];
        for (int r : ranks)
            minR = Math.min(minR, r);

        long left = 0;
        long right = (long) minR * cars * cars;
        // 开区间
        while (left + 1 < right) {
            long mid = (left + right) >> 1;
            long s = 0;
            for (int r : ranks)
                s += Math.sqrt(mid / r);

            if (s >= cars) {
                // 满足要求
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
}
