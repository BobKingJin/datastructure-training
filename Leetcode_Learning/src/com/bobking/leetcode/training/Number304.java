package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-05 12:25
 */
public class Number304 {

    // 参考：https://leetcode.cn/problems/range-sum-query-2d-immutable/solution/xia-ci-ru-he-zai-30-miao-nei-zuo-chu-lai-ptlo/
    private class NumMatrix {

        private int[][] sum;

        public NumMatrix(int[][] matrix) {

            int n = matrix.length;
            int m = n == 0 ? 0 : matrix[0].length;
            // 与「一维前缀和」一样，前缀和数组下标从 1 开始，因此设定矩阵形状为 [n + 1][m + 1]（模板部分）
            sum = new int[n + 1][m + 1];
            // 预处理除前缀和数组（模板部分）
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++)
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {

            // 求某一段区域和 [i, j] 的模板是 sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1]
            // 但由于源数组下标从 0 开始，因此要在模板的基础上进行 + 1
            row1++;
            col1++;
            row2++;
            col2++;
            return sum[row2][col2] - sum[row1 - 1][col2] - sum[row2][col1 - 1] + sum[row1 - 1][col1 - 1];
        }
    }
}
