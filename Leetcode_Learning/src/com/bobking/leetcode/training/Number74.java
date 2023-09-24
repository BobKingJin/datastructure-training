package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 0:14
 */
public class Number74 {

    public boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length - 1;
        int columns = 0;
        while (rows >= 0 && columns < matrix[0].length) {
            int num = matrix[rows][columns];
            if (num == target) {
                return true;
            } else if (num > target) {
                rows--;
            } else {
                columns++;
            }
        }

        return false;
    }
}
