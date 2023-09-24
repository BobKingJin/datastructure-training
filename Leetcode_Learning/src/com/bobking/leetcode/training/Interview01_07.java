package com.bobking.leetcode.training;

public class Interview01_07 {

    // 参考：程序猿代码指南P363
    public void rotate(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return;

        int leftRow = 0;
        int leftColumn = 0;
        int rightRow = matrix.length - 1;
        int rightColumn = matrix[0].length - 1;

        while (leftRow < rightRow && leftColumn < rightColumn)
            rotateEdge(matrix, leftRow++, leftColumn++, rightRow--, rightColumn--);
    }

    private void rotateEdge(int[][] matrix, int leftRow, int leftColumn, int rightRow, int rightColumn) {

        int times = rightColumn - leftColumn;
        int temp = 0;
        for (int i = 0; i < times; i++) {

            temp = matrix[leftRow][leftColumn + i];
            matrix[leftRow][leftColumn + i] = matrix[rightRow - i][leftColumn];
            matrix[rightRow - i][leftColumn] = matrix[rightRow][rightColumn - i];
            matrix[rightRow][rightColumn - i] = matrix[leftRow + i][rightColumn];
            matrix[leftRow + i][rightColumn] = temp;
        }
    }
}
