package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-01-17 10:28
 */
public class Number833 {

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {

        int len = s.length();
        int[] match = new int[len];
        Arrays.fill(match, -1);

        for (int i = 0; i < indices.length; ++i) {
            int ix = indices[i];
            if (ix + sources[i].length() < len + 1 && s.substring(ix, ix + sources[i].length()).equals(sources[i]) )
                match[ix] = i;
        }

        StringBuilder ans = new StringBuilder();
        int ix = 0;
        while (ix < len) {
            if (match[ix] >= 0) {
                ans.append(targets[match[ix]]);
                ix += sources[match[ix]].length();
            } else {
                ans.append(s.charAt(ix++));
            }
        }
        return ans.toString();
    }
}
