package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-05 7:25
 */
public class LCR129 {

    public boolean wordPuzzle(char[][] grid, String target) {

        char[] words = target.toCharArray();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (dfs(grid, words, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int i, int j, int index) {

        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[index])
            return false;

        if (index == word.length - 1)
            return true;

        char tmp = board[i][j];
        board[i][j] = '.';
        boolean res = dfs(board, word, i + 1, j, index + 1) || dfs(board, word, i - 1, j, index + 1)
                || dfs(board, word, i, j + 1, index + 1) || dfs(board, word, i, j - 1, index + 1);
        board[i][j] = tmp;
        return res;
    }
}
