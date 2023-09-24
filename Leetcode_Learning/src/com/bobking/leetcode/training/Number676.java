package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-11 23:03
 */
public class Number676 {

    // 参考：https://leetcode.cn/problems/implement-magic-dictionary/solution/by-ac_oier-a01l/
    private class MagicDictionary {

        int N = 100 * 100;
        int M = 26;
        int idx = 0;
        int[][] tr = new int[N][M];
        boolean[] isEnd = new boolean[N * M];

        public MagicDictionary() {

        }

        private void add(String s) {

            // 从第一行开始
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                int u = s.charAt(i) - 'a';
                if (tr[p][u] == 0)
                    tr[p][u] = ++idx;
                p = tr[p][u];
            }

            isEnd[p] = true;
        }

        private boolean query(String s, int idx, int p, int limit) {

            // idx 为匹配到字符串 s 的第几位
            // p 为字典树的角标
            if (limit < 0)
                return false;

            if (idx == s.length())
                return isEnd[p] && limit == 0;

            int u = s.charAt(idx) - 'a';

            for (int i = 0; i < 26; i++) {

                if (tr[p][i] == 0)
                    continue;

                if (query(s, idx + 1, tr[p][i], i == u ? limit : limit - 1))
                    return true;
            }

            return false;
        }

        public void buildDict(String[] dictionary) {
            for (String s : dictionary)
                add(s);
        }

        public boolean search(String searchWord) {
            return query(searchWord, 0, 0, 1);
        }
    }
}
