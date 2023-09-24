package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author BobKing
 * @create 2021-04-05 16:55
 */
public class Number130 {

    public void solve1(char[][] board) {

        if (board == null || board.length == 0)
            return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean isEdge = i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1;
                if (isEdge && board[i][j] == 'O')
                    dfs1(board, i, j);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs1(char[][] board, int i, int j) {

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#')
            return;

        board[i][j] = '#';
        dfs1(board, i - 1, j);
        dfs1(board, i + 1, j);
        dfs1(board, i, j - 1);
        dfs1(board, i, j + 1);
    }

    private class Position {

        public int i;
        public int j;

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public void solve2(char[][] board) {

        if (board == null || board.length == 0)
            return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean isEdge = i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs2(board, i, j);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs2(char[][] board, int i, int j) {

        Stack<Position> stack = new Stack<Position>();

        stack.push(new Position(i, j));
        board[i][j] = '#';

        while (!stack.isEmpty()) {
            // 取出当前 stack 顶, 不弹出
            Position pos = stack.peek();
            if (pos.i - 1 >= 0 && board[pos.i - 1][pos.j] == 'O') {
                stack.push(new Position(pos.i - 1, j));
                board[pos.i - 1][j] = '#';
                continue;
            }
            if (pos.i + 1 <= board.length - 1 && board[pos.i + 1][pos.j] == 'O') {
                stack.push(new Position(pos.i + 1, j));
                board[pos.i + 1][j] = '#';
                continue;
            }
            if (pos.j - 1 >= 0 && board[pos.i][pos.j - 1] == 'O') {
                stack.push(new Position(pos.i, pos.j - 1));
                board[pos.i][pos.j - 1] = '#';
                continue;
            }
            if (pos.j + 1 <= board[0].length - 1 && board[pos.i][pos.j + 1] == 'O') {
                stack.push(new Position(pos.i, pos.j + 1));
                board[pos.i][pos.j + 1] = '#';
                continue;
            }

            // 如果上下左右都搜索不到，本次搜索结束，弹出stack
            stack.pop();
        }
    }

    private void bfs(char[][] board, int i, int j) {

        Queue<Position> queue = new LinkedList<>();

        queue.offer(new Position(i, j));
        board[i][j] = '#';

        while (!queue.isEmpty()) {

            Position pos = queue.poll();
            if (pos.i - 1 >= 0 && board[pos.i - 1][pos.j] == 'O') {
                queue.offer(new Position(pos.i - 1, j));
                board[pos.i - 1][j] = '#';
            }
            if (pos.i + 1 <= board.length - 1 && board[pos.i + 1][pos.j] == 'O') {
                queue.offer(new Position(pos.i + 1, j));
                board[pos.i + 1][j] = '#';
            }
            if (pos.j - 1 >= 0 && board[pos.i][pos.j - 1] == 'O') {
                queue.offer(new Position(pos.i, pos.j - 1));
                board[pos.i][pos.j - 1] = '#';
            }
            if (pos.j + 1 <= board[0].length - 1 && board[pos.i][pos.j + 1] == 'O') {
                queue.offer(new Position(pos.i, pos.j + 1));
                board[pos.i][pos.j + 1] = '#';
            }
        }
    }
}
