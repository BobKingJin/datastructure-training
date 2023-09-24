package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-27 22:37
 */
public class Interview17_11 {

    public int findClosest(String[] words, String word1, String word2) {

        // 使用两个指针 p 和 q 分别记录最近的两个关键字的位置，并维护更新最小距离

        int n = words.length;
        int res = n;

        for (int i = 0, p = -1, q = -1; i < n; i++) {

            String t = words[i];
            if (t.equals(word1))
                p = i;
            if (t.equals(word2))
                q = i;
            if (p != -1 && q != -1)
                res = Math.min(res, Math.abs(p - q));
        }

        return res;
    }
}
