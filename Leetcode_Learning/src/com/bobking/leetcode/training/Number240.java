package com.bobking.leetcode.training;

public class Number240 {

    // 参考：程序猿代码指南P376
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length < 1)
            return false;

        int row = 0;
        int column = matrix[0].length - 1;

        while (row <= matrix.length - 1 && column >= 0) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }

        return false;
    }
}
