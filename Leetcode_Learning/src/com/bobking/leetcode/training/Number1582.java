package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-04 10:20
 */
public class Number1582 {

    public int numSpecial(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        int ans = 0;
        int[] r = new int[n];
        int[] c = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                r[i] += mat[i][j];
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                c[i] += mat[j][i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1 && r[i] == 1 && c[j] == 1)
                    ans++;
            }
        }

        return ans;
    }
}
