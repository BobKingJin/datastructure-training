package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-08-27 8:39
 */
public class Number720 {

    // 参考：https://leetcode.cn/problems/longest-word-in-dictionary/solution/by-ac_oier-bmot/
    public String longestWord1(String[] words) {

        String ans = "";
        Set<String> set = new HashSet<String>();
        for (String s : words)
            set.add(s);

        for (String s : set) {

            int n = s.length();
            int m = ans.length();

            if (n < m)
                continue;

            if (n == m && s.compareTo(ans) > 0)
                continue;

            boolean ok = true;

            for (int i = 1; i <= n && ok; i++) {
                String sub = s.substring(0, i);
                if (!set.contains(sub))
                    ok = false;
            }

            if (ok)
                ans = s;
        }

        return ans;
    }

    int N = 30010;
    int M = 26;
    int[][] tr = new int[N][M];
    boolean[] isEnd = new boolean[N];
    int idx = 0;

    private void add(String s) {
        int p = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (tr[p][u] == 0)
                tr[p][u] = ++idx;
            p = tr[p][u];
        }

        isEnd[p] = true;
    }

    private boolean query(String s) {

        int p = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            p = tr[p][u];
            if (!isEnd[p])
                return false;
        }

        return true;
    }

    // 参考：https://leetcode.cn/problems/longest-word-in-dictionary/solution/by-ac_oier-bmot/
    public String longestWord2(String[] words) {

        Arrays.fill(isEnd, false);
        for (int i = 0; i <= idx; i++)
            Arrays.fill(tr[i], 0);

        idx = 0;

        String ans = "";
        for (String s : words)
            add(s);

        for (String s : words) {
            int n = s.length();
            int m = ans.length();

            if (n < m)
                continue;

            if (n == m && s.compareTo(ans) > 0)
                continue;

            if (query(s))
                ans = s;
        }

        return ans;
    }


}
