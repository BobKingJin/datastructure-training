package com.bobking.leetcode.training;

public class Number79 {

    public boolean exist(char[][] board, String word) {

        if (board == null || board.length == 0
                || board[0] == null || board[0].length == 0
                || word == null || word.equals(""))
            return false;

        // 因为不能重复，所以需要设置一个标记数组
        // 因为除边界外，每个位置都有四个方向，有可能产生路径往回走的情况，所以需要设置一个标记数组
        boolean[][] marked = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backTrace1(board, word, i, j, 0, marked))
                    return true;
            }
        }

        return false;
    }

    private boolean backTrace1(char[][] board, String word, int row, int column, int index, boolean[][] marked) {

        if (row >= 0 && row <= board.length - 1 && column >= 0 && column <= board[0].length - 1) {
            if (index == word.length() - 1)
                return board[row][column] == word.charAt(index);

            if (!marked[row][column] && board[row][column] == word.charAt(index)) {
                marked[row][column] = true;
                // 上下左右
                if (backTrace1(board, word, row - 1, column, index + 1, marked))
                    return true;
                if (backTrace1(board, word, row, column - 1, index + 1, marked))
                    return true;
                if (backTrace1(board, word, row + 1, column, index + 1, marked))
                    return true;
                if (backTrace1(board, word, row, column + 1, index + 1, marked))
                    return true;
                // 回溯
                marked[row][column] = false;
            }
        }

        return false;
    }

    // 参考：https://leetcode-cn.com/problems/word-search/solution/zai-er-wei-ping-mian-shang-shi-yong-hui-su-fa-pyth/
    private boolean backTrace2(char[][] board, String word, int row, int column, int index, boolean[][] marked) {

        if (index == word.length() - 1)
            return board[row][column] == word.charAt(index);

        if (board[row][column] == word.charAt(index)) {
            marked[row][column] = true;
            if (isLegal(row - 1, column, board.length - 1, board[0].length - 1) && !marked[row - 1][column]) {
                if (backTrace2(board, word, row - 1, column, index + 1, marked))
                    return true;
            }
            if (isLegal(row, column - 1, board.length - 1, board[0].length - 1) && !marked[row][column - 1]) {
                if (backTrace2(board, word, row, column - 1, index + 1, marked))
                    return true;
            }
            if (isLegal(row + 1, column, board.length - 1, board[0].length - 1) && !marked[row + 1][column]) {
                if (backTrace2(board, word, row + 1, column, index + 1, marked))
                    return true;
            }
            if (isLegal(row, column + 1, board.length - 1, board[0].length - 1) && !marked[row][column + 1]) {
                if (backTrace2(board, word, row, column + 1, index + 1, marked))
                    return true;
            }
            // 回溯
            marked[row][column] = false;
        }

        return false;
    }

    private boolean isLegal(int row, int column, int maxRow, int maxColumn) {
        return row >= 0 && row <= maxRow && column >= 0 && column <= maxColumn;
    }
}
