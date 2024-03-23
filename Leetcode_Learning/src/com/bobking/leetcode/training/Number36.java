package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @author BobKing
 * @create 2021-04-03 23:37
 */
public class Number36 {

    // 参考：https://leetcode-cn.com/problems/valid-sudoku/solution/you-xiao-de-shu-du-by-leetcode/
    public boolean isValidSudoku(char[][] board) {

        if (board == null || board.length == 0)
            return false;

        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer>[] columns = new HashMap[9];
        HashMap<Integer, Integer>[] boxes = new HashMap[9];

        for (int i = 0; i < board.length; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    int num = ch - '0';
                    rows[i].put(num, rows[i].getOrDefault(num, 0) + 1);
                    columns[j].put(num, columns[j].getOrDefault(num, 0) + 1);
                    // boxIndex = (row / 3) * 3 + columns / 3
                    int boxIndex = (i / 3) * 3 + (j / 3);
                    boxes[boxIndex].put(num, boxes[boxIndex].getOrDefault(num, 0) + 1);
                    if (rows[i].get(num) > 1 || columns[j].get(num) > 1 || boxes[boxIndex].get(num) > 1)
                        return false;
                }
            }
        }
        return true;
    }
}
