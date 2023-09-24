package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-03-25 0:08
 */
public class Number1032 {

    // 参考：https://leetcode.cn/problems/stream-of-characters/solution/by-ac_oier-ihd4/
    private class StreamChecker1 {

        int N = 2010 * 200;
        int idx = 0;
        int[][] tr = new int[N][26];
        boolean[] isEnd = new boolean[N * 26];
        StringBuilder sb = new StringBuilder();

        private void add(String s) {
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                int u = s.charAt(i) - 'a';
                if (tr[p][u] == 0)
                    tr[p][u] = ++idx;
                p = tr[p][u];
            }
            isEnd[p] = true;
        }

        private boolean query(int start, int end) {
            int p = 0;
            for (int i = start; i <= end; i++) {
                int u = sb.charAt(i) - 'a';
                if (tr[p][u] == 0)
                    return false;
                p = tr[p][u];
            }
            return isEnd[p];
        }

        public StreamChecker1(String[] words) {

            for (int i = 0; i <= idx; i++) {
                Arrays.fill(tr[i], 0);
                isEnd[i] = false;
            }

            idx = 0;
            for (String s : words)
                add(s);
        }

        public boolean query(char letter) {
            sb.append(letter);
            int n = sb.length();
            int min = Math.max(0, n - 200);
            for (int i = n - 1; i >= min; i--) {
                if (query(i, n - 1))
                    return true;
            }
            return false;
        }
    }

    // https://leetcode.cn/problems/stream-of-characters/solution/by-ac_oier-ihd4/
    private class StreamChecker2 {

        int N = 2010 * 200;
        int idx = 0;
        int[][] tr = new int[N][26];
        boolean[] isEnd = new boolean[N * 26];
        StringBuilder sb = new StringBuilder();

        private void add(String s) {
            int p = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                int u = s.charAt(i) - 'a';
                if (tr[p][u] == 0)
                    tr[p][u] = ++idx;
                p = tr[p][u];
            }
            isEnd[p] = true;
        }

        public StreamChecker2(String[] words) {
            for (int i = 0; i <= idx; i++) {
                Arrays.fill(tr[i], 0);
                isEnd[i] = false;
            }
            idx = 0;
            for (String s : words)
                add(s);
        }
        public boolean query(char c) {
            sb.append(c);
            int n = sb.length();
            int min = Math.max(0, n - 200);
            int p = 0;
            for (int i = n - 1; i >= min; i--) {
                if (isEnd[p])
                    return true;
                int u = sb.charAt(i) - 'a';
                if (tr[p][u] == 0)
                    return false;
                p = tr[p][u];
            }
            return isEnd[p];
        }
    }
}
