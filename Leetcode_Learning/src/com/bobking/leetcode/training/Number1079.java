package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-16 7:33
 */
public class Number1079 {

    public int numTilePossibilities(String tiles) {

        int[] count = new int[26];
        char[] charArray = tiles.toCharArray();
        for (char c : charArray)
            count[c - 'A']++;

        return dfs(count);
    }

    private int dfs(int[] count) {

        // 递归终止条件：当前没有可以用的字符（没有显示递归终止条件）
        int res = 0;

        for (int i = 0; i < 26; i++) {

            if (count[i] == 0)
                continue;
            res++;
            count[i]--;

            res += dfs(count);
            count[i]++;
        }
        return res;
    }

}
