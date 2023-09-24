package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-23 7:59
 */
public class Number2643 {

    public int[] rowAndMaximumOnes(int[][] mat) {

        int ans = 0;
        int count = 0;
        int mark = 0;

        for (int i = 0; i < mat.length; ++i) {
            count = 0;
            for (int j = 0; j < mat[i].length; ++j) {
                if (mat[i][j] == 1)
                    ++count;
            }
            if (ans < count) {
                ans = count;
                mark = i;
            }
        }
        return new int[]{mark, ans};
    }
}
