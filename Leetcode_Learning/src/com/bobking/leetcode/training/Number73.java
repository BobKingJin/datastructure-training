package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-04-04 12:08
 */
public class Number73 {

    // 参考：https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
    public void setZeroes1(int[][] matrix) {

        if (matrix == null || matrix.length == 0)
            return;

        // 两个标记数组，标记有哪些行和列出现了 0
        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rows[i] || columns[j])
                    matrix[i][j] = 0;
            }
        }
    }

    // 参考：https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
    public void setZeroes2(int[][] matrix) {

        if (matrix == null || matrix.length == 0)
            return;

        // 两个标记变量,标记第一行和第一列是否出现了 0
        boolean firstRowHas0 = false;
        boolean firstColHas0 = false;

        // 第一行是否有 0
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0)
                firstRowHas0 = true;
        }

        // 第一列是否有0
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0)
                firstColHas0 = true;
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRowHas0) {
            for (int i = 0; i < matrix[0].length; i++)
                matrix[0][i] = 0;
        }
        if (firstColHas0) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }
    }

    // 参考：https://leetcode-cn.com/problems/set-matrix-zeroes/solution/ju-zhen-zhi-ling-by-leetcode-solution-9ll7/
    public void setZeroes3(int[][] matrix) {

        if (matrix == null || matrix.length == 0)
            return;

        // 一个标记变量，第一列是否出现了 0 而 matrix[0][0] 表示第一行是否出现了 0
        boolean firstColHas0 = false;

        for (int i = 0; i < matrix.length; i++) {

            if (matrix[i][0] == 0)
                firstColHas0 = true;

            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
            }
            if (firstColHas0)
                matrix[i][0] = 0;
        }
    }
}
