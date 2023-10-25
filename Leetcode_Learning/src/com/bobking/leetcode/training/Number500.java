package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-10-24 8:00
 */
public class Number500 {

    String[] ss = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    int[] hash = new int[26];

    private void init() {
        for (int i = 0; i < ss.length; i++) {
            for (char c : ss[i].toCharArray())
                hash[c - 'a'] = i;
        }
    }

    public String[] findWords(String[] words) {

        init();

        List<String> list = new ArrayList<String>();
        out:
        for (String w : words) {
            int t = -1;
            for (char c : w.toCharArray()) {
                c = Character.toLowerCase(c);
                if (t == -1) {
                    t = hash[c - 'a'];
                } else if (t != hash[c - 'a']) {
                    continue out;
                }
            }
            list.add(w);
        }
        return list.toArray(new String[list.size()]);
    }
}
