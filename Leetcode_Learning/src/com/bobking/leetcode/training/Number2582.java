package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-07 7:49
 */
public class Number2582 {

    public int passThePillow(int n, int time) {
        // 到达端点需要 n − 1 时间
        int t = time % (n - 1);
        // 偶数，说明正在从 1 到 n，答案为 1 + t
        // 奇数，说明正在从 n 到 1，答案为 n − t
        return time / (n - 1) % 2 > 0 ? n - t : 1 + t;
    }
}
