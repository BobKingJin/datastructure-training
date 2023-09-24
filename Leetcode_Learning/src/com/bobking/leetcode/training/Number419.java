package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-31 12:32
 */
public class Number419 {

    // 参考：https://leetcode.cn/problems/battleships-in-a-board/solution/gong-shui-san-xie-ji-chong-sao-miao-xian-trmc/
    public int countBattleships1(char[][] board) {

        int m = board.length;
        int n = board[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && board[i - 1][j] == 'X')
                    continue;
                if (j > 0 && board[i][j - 1] == 'X')
                    continue;
                if (board[i][j] == 'X')
                    ans++;
            }
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/battleships-in-a-board/solution/gong-shui-san-xie-ji-chong-sao-miao-xian-trmc/
    public int countBattleships2(char[][] board) {

        int m = board.length;
        int n = board[0].length;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] != 'X')
                    continue;

                board[i][j] = '-';
                for (int k = i + 1; k < m && board[k][j] == 'X'; k++)
                    board[k][j] = '-';
                for (int k = j + 1; k < n && board[i][k] == 'X'; k++)
                    board[i][k] = '-';
                ans++;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                if (board[i][j] == '-')
                    board[i][j] = 'X';
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/battleships-in-a-board/solution/gong-shui-san-xie-ji-chong-sao-miao-xian-trmc/
    public int countBattleships3(char[][] board) {

        int m = board.length;
        int n = board[0].length;
        int ans = 0;

        boolean[][] vis = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] != 'X' || vis[i][j])
                    continue;

                vis[i][j] = true;
                for (int k = i + 1; k < m && board[k][j] == 'X'; k++)
                    vis[k][j] = true;
                for (int k = j + 1; k < n && board[i][k] == 'X'; k++)
                    vis[i][k] = true;
                ans++;
            }
        }

        return ans;
    }


}
