package com.bobking.leetcode.training;

public class Number48 {

    // 参考：程序猿代码指南P363
    public void rotate(int[][] matrix) {

        if (matrix == null || matrix.length < 1)
            return;

        // 左上角行标和列标
        int lRow = 0;
        int lColumn = 0;
        // 右下角行标和列标
        int rRow = matrix.length - 1;
        int rColumn = matrix[0].length - 1;

        while (lRow < rRow)
            rotateEdge(matrix, lRow++, lColumn++, rRow--, rColumn--);
    }

    private void rotateEdge(int[][] matrix, int lRow, int lColumn, int rRow, int rColumn) {

        int times = rColumn - lColumn;
        int tmp = 0;
        for (int i = 0; i < times; i++) {
            tmp = matrix[lRow][lColumn + i];
            matrix[lRow][lColumn + i] = matrix[rRow - i][lColumn];
            matrix[rRow - i][lColumn] = matrix[rRow][rColumn - i];
            matrix[rRow][rColumn - i] = matrix[lRow + i][rColumn];
            matrix[lRow + i][rColumn] = tmp;
        }
    }
}
