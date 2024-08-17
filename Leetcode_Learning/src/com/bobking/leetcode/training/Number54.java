package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-04-04 11:07
 */
public class Number54 {

    // 参考：程序猿代码指南P361
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return res;

        // 左上角角标
        int leftRow = 0;
        int leftColumn = 0;
        // 右下角角标
        int rightRow = matrix.length - 1;
        int rightColumn = matrix[0].length - 1;

        while (leftRow <= rightRow && leftColumn <= rightColumn) {
            // 只有一行
            if (leftRow == rightRow) {
                for (int i = leftColumn; i <= rightColumn; i++)
                    res.add(matrix[leftRow][i]);
                // 只有一列
            } else if (leftColumn == rightColumn) {
                for (int i = leftRow; i <= rightRow; i++)
                    res.add(matrix[i][leftColumn]);
            } else {
                int curRow = leftRow;
                int curColumn = leftColumn;
                while (curColumn < rightColumn)
                    res.add(matrix[leftRow][curColumn++]);
                while (curRow < rightRow)
                    res.add(matrix[curRow++][rightColumn]);
                while (curColumn > leftColumn)
                    res.add(matrix[rightRow][curColumn--]);
                while (curRow > leftRow)
                    res.add(matrix[curRow--][leftColumn]);
            }
            leftRow++;
            leftColumn++;
            rightRow--;
            rightColumn--;
        }

        return res;
    }
}
