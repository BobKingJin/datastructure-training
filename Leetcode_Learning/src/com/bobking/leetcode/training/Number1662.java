package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-13 11:18
 */
public class Number1662 {

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        int n = word1.length;
        int m = word2.length;
        int i = 0;
        int j = 0;
        // 使用变量 p 和 q 代表当前比较到 word1[i] 和 word2[j] 的哪一位
        int p = 0;
        int q = 0;

        while (i < n && j < m) {

            if (word1[i].charAt(p++) != word2[j].charAt(q++))
                return false;

            if (p == word1[i].length()){
                i++;
                p = 0;
            }

            if (q == word2[j].length()){
                j++;
                q = 0;
            }
        }
        return i == n && j == m;
    }

}
