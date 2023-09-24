package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-13 11:34
 */
public class Number2287 {

    public int rearrangeCharacters(String s, String target) {

        int[] cntsT = new int[26];
        for (int i = 0; i < target.length(); ++i)
            cntsT[target.charAt(i) - 'a']++;

        int[] cntsS = new int[26];
        for (int i = 0; i < s.length(); ++i)
            cntsS[s.charAt(i) - 'a']++;

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 26; ++i) {
            if (cntsT[i] == 0)
                continue;
            res = Math.min(res, cntsS[i] / cntsT[i]);
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
