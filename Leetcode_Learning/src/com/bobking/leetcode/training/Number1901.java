package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/19 8:00
 * @Author: BobKing
 * @Description:
 */
public class Number1901 {

    public int[] findPeakGrid(int[][] mat) {

        int left = 0;
        int right = mat[0].length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int[] arr = getColMax(mat, mid);
            int max = arr[0];
            int maxIndex = arr[1];
            if (mid == 0 || (left == 0 && right == 1)) {
                if (max > mat[maxIndex][1]) {
                    return new int[]{maxIndex, 0};
                } else {
                    left = 1;
                }
            } else {
                if (mat[maxIndex][mid - 1] < max && max > mat[maxIndex][mid + 1]) {
                    return new int[]{maxIndex, mid};
                } else if (mat[maxIndex][mid - 1] < max && max < mat[maxIndex][mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return new int[]{getColMax(mat, left)[1], left};
    }

    // 返回当列中最大值及其下标
    public int[] getColMax(int[][] mat, int col) {

        int max = mat[0][col];
        int index = 0;

        for (int i = 1; i < mat.length; i++) {
            if (max < mat[i][col]) {
                index = i;
                max = mat[i][col];
            }
        }
        return new int[]{max, index};
    }
}
