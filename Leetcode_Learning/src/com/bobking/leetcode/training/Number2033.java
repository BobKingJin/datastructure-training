package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-10-17 19:10
 */
public class Number2033 {

    // 思路参考：https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid/solution/zhong-wei-shu-by-endlesscheng-p0vj/
    // 代码参考：https://leetcode.cn/problems/minimum-operations-to-make-a-uni-value-grid/solution/javatan-xin-qu-zhong-wei-shu-by-fei-xiao-v84g/
    public int minOperations(int[][] grid, int x) {

        int n = grid.length;
        int m = grid[0].length;
        int[] arr = new int[m * n];
        int i = 0;

        for (int[] g : grid) {
            for (int a : g)
                arr[i++] = a;
        }

        Arrays.sort(arr);
        int j = arr[(n * m) / 2];
        int sum = 0;

        for (int a : arr) {

            int l = Math.abs(j - a);

            if (l % x != 0)
                return -1;

            sum += l / x;
        }

        return sum;
    }
}
