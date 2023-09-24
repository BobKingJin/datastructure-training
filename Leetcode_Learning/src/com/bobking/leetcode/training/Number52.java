package com.bobking.leetcode.training;

public class Number52 {

    // 参考：程序猿代码指南P249
    public int totalNQueens(int n) {

        if (n < 1)
            return 0;
        // record[i] 表示第 i 行皇后所在的列数
        int[] record = new int[n];

        return recursion(0, record, n);
    }

    // 按行进行递归
    private int recursion(int row, int[] record, int n) {

        if (row == n)
            return 1;

        int res = 0;
        // 尝试每一列
        for (int j = 0; j < n; j++) {
            if (isValid(record, row, j)) {
                // 每行的每列的值都可以重置，因此 record[row] 可以不用回溯
                record[row] = j;
                res += recursion(row + 1, record, n);
            }
        }

        return res;
    }

    private boolean isValid(int[] record, int row, int col) {

        for (int i = 0; i < row; i++) {
            // 列不能冲突            对角线不能冲突
            if (record[i] == col || Math.abs(record[i] - col) == Math.abs(row - i))
                return false;
        }

        return true;
    }
}
