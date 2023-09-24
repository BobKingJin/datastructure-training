package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-07-09 9:57
 */
public class Number849 {

    // 参考：https://leetcode.cn/problems/maximize-distance-to-closest-person/solution/dao-zui-jin-de-ren-de-zui-da-ju-chi-by-leetcode/
    public int maxDistToClosest1(int[] seats) {

        int N = seats.length;
        // 令 left[i] 为座位 i 到坐在 i 左边的人的最近距离
        // 同理 right[i] 为座位 i 到坐在 i 右边的人的最近距离
        int[] left = new int[N];
        int[] right = new int[N];
        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                left[i] = 0;
            } else if (i > 0) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = N - 1; i >= 0; --i) {
            if (seats[i] == 1) {
                right[i] = 0;
            } else if (i < N - 1) {
                right[i] = right[i + 1] + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < N; ++i)
            if (seats[i] == 0)
                ans = Math.max(ans, Math.min(left[i], right[i]));

        return ans;
    }

    // 参考：https://leetcode.cn/problems/maximize-distance-to-closest-person/solution/dao-zui-jin-de-ren-de-zui-da-ju-chi-by-leetcode/
    public int maxDistToClosest2(int[] seats) {

        int N = seats.length;
        // 使用 prev 记录 i 最左边第一个有人的位置，future 记录 i 最右边第一个有人的位置
        // 座位 i 到最近的人的距离为 min(i - prev, future - i)
        // 有一种特殊情况，如果座位 i 左边没有人，则认为到左边第一个人的距离是无限大，右边同理
        int prev = -1;
        int future = 0;
        int ans = 0;

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) {
                prev = i;
            } else {
                while ((future < N && seats[future] == 0) || future < i)
                    future++;

                int left = prev == -1 ? N : i - prev;
                int right = future == N ? N : future - i;
                ans = Math.max(ans, Math.min(left, right));
            }
        }

        return ans;
    }
}
