package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Jianzhi29 {

    // 参考：程序猿代码指南P361
    public int[] spiralOrder1(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return new int[0];

        int leftRow = 0;
        int leftColumn = 0;
        int rightRow = matrix.length - 1;
        int rightColumn = matrix[0].length - 1;

        List<Integer> list = new ArrayList<Integer>();

        while (leftRow <= rightRow && leftColumn <= rightColumn)
            printEdge(matrix, list, leftRow++, leftColumn++, rightRow--, rightColumn--);

        int[] res = new int[list.size()];

        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);

        return res;
    }

    private void printEdge(int[][] matrix, List<Integer> list, int leftRow, int leftColumn, int rightRow, int rightColumn) {

        // 单行
        if (leftRow == rightRow) {
            for (int i = leftColumn; i <= rightColumn; i++)
                list.add(matrix[leftRow][i]);
        // 单列
        } else if (leftColumn == rightColumn) {
            for (int i = leftRow; i <= rightRow; i++)
                list.add(matrix[i][leftColumn]);
        } else {

            for (int i = leftColumn; i < rightColumn; i++)
                list.add(matrix[leftRow][i]);
            for (int i = leftRow; i < rightRow; i++)
                list.add(matrix[i][rightColumn]);
            for (int i = rightColumn; i > leftColumn; i--)
                list.add(matrix[rightRow][i]);
            for (int i = rightRow; i > leftRow; i--)
                list.add(matrix[i][leftColumn]);
        }
    }

    // 参考：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/mian-shi-ti-29-shun-shi-zhen-da-yin-ju-zhen-she-di/
    public int[] spiralOrder2(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return new int[0];

        int leftRow = 0;
        int leftColumn = 0;
        int rightRow = matrix.length - 1;
        int rightColumn = matrix[0].length - 1;
        int index = 0;

        int[] res = new int[(rightColumn + 1) * (rightRow + 1)];
        while (true) {
            for (int i = leftColumn; i <= rightColumn; i++)
                res[index++] = matrix[leftRow][i]; // left to right.
            if (++leftRow > rightRow)
                break;
            for (int i = leftRow; i <= rightRow; i++)
                res[index++] = matrix[i][rightColumn]; // top to bottom.
            if (leftColumn > --rightColumn)
                break;
            for (int i = rightColumn; i >= leftColumn; i--)
                res[index++] = matrix[rightRow][i]; // right to left.
            if (leftRow > --rightRow)
                break;
            for (int i = rightRow; i >= leftRow; i--)
                res[index++] = matrix[i][leftColumn]; // bottom to top.
            if (++leftColumn > rightColumn)
                break;
        }

        return res;
    }
}
