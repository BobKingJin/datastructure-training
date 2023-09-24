package com.bobking.leetcode.training;

public class Number37 {

    // 参考：https://leetcode-cn.com/problems/sudoku-solver/solution/hui-su-fa-jie-shu-du-by-i_use_python/
    public void solveSudoku1(char[][] board) {

        // 三个布尔数组表明 行，列，还有 3 * 3 的方格的数字是否被使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        // 初始化
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if (1 <= num && num <= 9) {
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;
                }
            }
        }
        // 递归尝试填充数组
        recursion(board, rowUsed, colUsed, boxUsed, 0, 0);
    }

    // 按列递归
    private boolean recursion(char[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][][] boxUsed, int row, int col) {

        // 边界校验, 如果已经填充完成, 返回 true, 表示一切结束
        if (col == board[0].length) {

            col = 0;
            // 穷举到最后一列的话就换到下一列重新开始
            // 因为是按列递归的，所以当到达最后一列时，row++
            row++;
            if (row == board.length)
                return true;
        }

        // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
        if (board[row][col] == '.') {
            // 尝试填充 1 ~ 9
            for (int num = 1; num <= 9; num++) {
                boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row / 3][col / 3][num]);
                if (canUsed) {

                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row / 3][col / 3][num] = true;

                    board[row][col] = (char) ('0' + num);
                    // 按列递归
                    if (recursion(board, rowUsed, colUsed, boxUsed, row, col + 1))
                        return true;
                    // 回溯
                    board[row][col] = '.';

                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row / 3][col / 3][num] = false;
                }
            }
        } else {
            // 按列递归
            return recursion(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }
        return false;
    }

    // 参考：https://mp.weixin.qq.com/s/VCirGskFGPln-S2LGFTgKg
    public void solveSudoku2(char[][] board) {

        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int r, int c) {

        // 因为行和列是固定的，所以可以固定 m 和 n，每次递归，m 和 n 都是固定的，所以每次重新赋值对结果没影响
        // 其实也可以在backtrack() 递归函数上面定义 m 和 n 这样就不用每次重新赋值
        int m = 9;
        int n = 9;
        if (c == n)
            // 穷举到最后一列的话就换到下一行重新开始
            return backtrack(board, r + 1, 0);

        if (r == m)
            // 找到一个可行解，触发 base case
            return true;

        // 就是对每个位置进行穷举
        for (int i = r; i < m; i++) {
            for (int j = c; j < n; j++) {

                if (board[i][j] != '.')
                    // 如果有预设数字，不用我们穷举
                    return backtrack(board, i, j + 1);

                for (char ch = '1'; ch <= '9'; ch++) {
                    // 如果遇到不合法的数字，就跳过
                    if (!isValid(board, i, j, ch))
                        continue;

                    board[i][j] = ch;
                    // 如果找到一个可行解，立即结束
                    if (backtrack(board, i, j + 1))
                        return true;

                    board[i][j] = '.';
                }
                // 穷举完 1 ~ 9，依然没有找到可行解，此路不通
                return false;
            }
        }

        return false;
    }

    private boolean isValid(char[][] board, int r, int c, char n) {

        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n)
                return false;
            // 判断列是否存在重复
            if (board[i][c] == n)
                return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == n)
                return false;
        }
        return true;
    }

}
