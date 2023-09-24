package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-21 9:59
 */
public class Number1455 {

    public int isPrefixOfWord(String sentence, String searchWord) {

        String[] ss = sentence.split(" ");
        int n = ss.length;
        int m = searchWord.length();

        for (int i = 0; i < n; i++) {
            if (ss[i].length() < m)
                continue;

            boolean ok = true;

            for (int j = 0; j < m && ok; j++) {
                if (ss[i].charAt(j) != searchWord.charAt(j))
                    ok = false;
            }

            if (ok)
                return i + 1;
        }
        return -1;
    }
}
