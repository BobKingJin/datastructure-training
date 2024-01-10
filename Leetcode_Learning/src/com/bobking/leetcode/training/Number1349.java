package com.bobking.leetcode.training;

import java.util.stream.IntStream;

/**
 * @Date: 2023/12/26 7:34
 * @Author: BobKing
 * @Description:
 */
public class Number1349 {

    // 参考: https://leetcode.cn/problems/maximum-students-taking-exam/solutions/90312/xiang-jie-ya-suo-zhuang-tai-dong-tai-gui-hua-jie-f/?envType=daily-question&envId=2023-12-26
    public int maxStudents(char[][] seats) {

        int n = seats.length;
        int m = seats[0].length;

        // dp[i][j] 表示当第 i 行的座位分布为 j 时，前 i 行可容纳的最大学生人数
        int[][] dp = new int[n + 1][1 << m];

        for (int i = 1; i <= n; i++) {
            int invalid = 0;
            for (int j = 0; j < m; j++) {
                if (seats[i - 1][j] == '#')
                    // 1: 表示坏了
                    invalid |= 1 << j;
            }
            for (int j = 0; j < (1 << m); j++) {
                // 来判断相邻位置
                int adjacentMask = j << 1;
                // 坐在坏椅子上或相邻座位已坐，舍弃该状态
                if ((j & invalid) != 0 || (j & adjacentMask) != 0) {
                    dp[i][j] = -1;
                    continue;
                }

                int theOtherAdjacentMask = j >>> 1;
                // 如果状态可行，遍历上一行的所有状态，寻找状态最大值
                for (int s = 0; s < (1 << m); s++) {
                    // 如果 s 不合法，舍弃状态 s
                    if (dp[i - 1][s] == -1)
                        continue;
                    // 如果相邻列已坐，舍弃状态 s
                    if ((s & adjacentMask) != 0 || (s & theOtherAdjacentMask) != 0)
                        continue;
                    // 状态转移
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][s] + Integer.bitCount(j));
                }
            }
        }
        return IntStream.of(dp[n]).max().getAsInt();
    }
}
