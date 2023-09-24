package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-06-27 16:12
 */
public class Number59 {

    // 参考：程序猿代码指南P361
    // 参考：https://leetcode-cn.com/problems/spiral-matrix-ii/solution/spiral-matrix-ii-mo-ni-fa-she-ding-bian-jie-qing-x/
    public int[][] generateMatrix(int n) {

        if (n < 1)
            return null;

        int[][] res = new int[n][n];

        // 左上角角标
        int leftRow = 0;
        int leftColumn = 0;
        // 右下角角标
        int rightRow = n - 1;
        int rightColumn = n - 1;
        int count = 1;

        while (leftRow <= rightRow && leftColumn <= rightColumn) {
            // 只有一行
            if (leftRow == rightRow) {
                for (int i = leftColumn; i <= rightColumn; i++)
                    res[leftRow][i] = count++;
                // 只有一列
            } else if (leftColumn == rightColumn) {
                for (int i = leftRow; i <= rightRow; i++)
                    res[i][leftColumn] = count++;
            } else {
                int curRow = leftRow;
                int curColumn = leftColumn;
                while (curColumn < rightColumn)
                    res[leftRow][curColumn++] = count++;

                while (curRow < rightRow)
                    res[curRow++][rightColumn] = count++;

                while (curColumn > leftColumn)
                    res[rightRow][curColumn--] = count++;

                while (curRow > leftRow)
                    res[curRow--][leftColumn] = count++;
            }

            leftRow++;
            leftColumn++;
            rightRow--;
            rightColumn--;
        }

        return res;
    }
}
