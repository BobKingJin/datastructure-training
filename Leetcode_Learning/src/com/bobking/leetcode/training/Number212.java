package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-04-30 11:49
 */
public class Number212 {

    Set<String> set = new HashSet<String>();
    List<String> res = new ArrayList<String>();
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m;
    int n;
    boolean[][] vis = new boolean[15][15];

    public List<String> findWords1(char[][] board, String[] words) {
        
        m = board.length;
        n = board[0].length;
        for (String w : words)
            set.add(w);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = true;
                sb.append(board[i][j]);
                dfs(board, i, j, sb);
                // 回溯
                vis[i][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return res;
    }

    private void dfs(char[][] board, int i, int j, StringBuilder sb) {

        // 1 <= words[i].length <= 10
        if (sb.length() > 10)
            return ;

        if (set.contains(sb.toString())) {
            res.add(sb.toString());
            // 注意，不要重复添加
            set.remove(sb.toString());
        }

        // 上下左右
        for (int[] d : dirs) {

            int dx = i + d[0];
            int dy = j + d[1];
            if (dx < 0 || dx >= m || dy < 0 || dy >= n)
                continue;

            if (vis[dx][dy])
                continue;

            vis[dx][dy] = true;
            sb.append(board[dx][dy]);
            dfs(board, dx, dy, sb);
            // 回溯
            vis[dx][dy] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private class TrieNode {
        String s;
        TrieNode[] tns = new TrieNode[26];
    }
    
    private void insert(String s) {
        
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.tns[u] == null) 
                p.tns[u] = new TrieNode();
            p = p.tns[u];
        }
        
        p.s = s;
    }

    TrieNode root = new TrieNode();

    public List<String> findWords(char[][] board, String[] words) {

        m = board.length; 
        n = board[0].length;
        // 构建字典树
        for (String w : words) 
            insert(w);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int u = board[i][j] - 'a';
                if (root.tns[u] != null) {
                    vis[i][j] = true;
                    dfs(board, i, j, root.tns[u]);
                    // 回溯
                    vis[i][j] = false;
                }
            }
        }
        
        List<String> res = new ArrayList<String>();
        for (String s : set) 
            res.add(s);
        
        return res;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node) {

        if (node.s != null)
            set.add(node.s);

        for (int[] d : dirs) {
            int dx = i + d[0];
            int dy = j + d[1];

            if (dx < 0 || dx >= m || dy < 0 || dy >= n)
                continue;

            if (vis[dx][dy])
                continue;

            int u = board[dx][dy] - 'a';
            if (node.tns[u] != null) {
                vis[dx][dy] = true;
                dfs(board, dx, dy, node.tns[u]);
                vis[dx][dy] = false;
            }
        }
    }

}
