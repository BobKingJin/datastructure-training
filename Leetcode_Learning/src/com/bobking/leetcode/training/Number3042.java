package com.bobking.leetcode.training;

/**
 * @Date: 2024/2/22 23:43
 * @Author: BobKing
 * @Description:
 */
public class Number3042 {

    private class Node {
        Node[] son = new Node[26];
        int cnt;
    }

    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        Node root = new Node();
        for (String T : words) {
            char[] t = T.toCharArray();
            int n = t.length;
            int[] z = new int[n];
            int l = 0;
            int r = 0;
            for (int i = 1; i < n; i++) {
                if (i <= r)
                    z[i] = Math.min(z[i - l], r - i + 1);
                while (i + z[i] < n && t[z[i]] == t[i + z[i]]) {
                    l = i;
                    r = i + z[i];
                    z[i]++;
                }
            }

            z[0] = n;

            Node cur = root;
            for (int i = 0; i < n; i++) {
                int c = t[i] - 'a';
                if (cur.son[c] == null)
                    cur.son[c] = new Node();
                cur = cur.son[c];
                if (z[n - 1 - i] == i + 1) // t 的长为 i+1 的前后缀相同
                    ans += cur.cnt;
            }
            cur.cnt++;
        }
        return ans;
    }
}
