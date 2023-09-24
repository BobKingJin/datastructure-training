package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number279 {

    // 参考：https://leetcode-cn.com/problems/perfect-squares/solution/hua-jie-suan-fa-279-wan-quan-ping-fang-shu-by-guan/
    public int numSquares1(int n) {

        if (n <= 0)
            return 0;
        // dp[j] 表示和为 j 的完全平方数的最少数量
        // 默认初始化值都为 0
        int[] dp = new int[n + 1];
        // 从前往后
        for (int i = 1; i <= n; i++) {
            // 最坏的情况就是 dp[i] = i
            dp[i] = i;
            // 注意这里 j 不需要一个个尝试，直接以 i - j * j 为步长进行遍历
            for (int j = 1; i - j * j >= 0; j++)
                // 后面依赖前面
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        }

        return dp[n];
    }

    public int numSquares2(int n) {

        if (n <= 0)
            return 0;

        int[] dp = new int[n + 1];
        List<Integer> squaresList = generateSquaresList(n);

        for (int i = 1; i <= n; i++) {

            int min = Integer.MAX_VALUE;

            for (Integer square : squaresList) {
                if (square > i)
                    break;
                min = Math.min(min, dp[i - square] + 1);
            }

            dp[i] = min;
        }

        return dp[n];
    }

    // 这里生成一个小于 n 的一个 list：1, 4, 9, 16......
    private List<Integer> generateSquaresList(int n) {

        List<Integer> squaresList = new ArrayList<Integer>();

        if (n <= 0)
            return squaresList;

        int add = 3;
        int square = 1;
        while (square <= n) {
            squaresList.add(square);
            square += add;
            add += 2;
        }

        return squaresList;
    }
}
