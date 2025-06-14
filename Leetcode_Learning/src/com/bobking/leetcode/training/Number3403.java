package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/14 12:05
 * @Author: BobKing
 * @Description:
 */
public class Number3403 {

    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            String sub = word.substring(i, Math.min(i + n - numFriends + 1, n));
            if (sub.compareTo(ans) > 0) {
                ans = sub;
            }
        }
        return ans;
    }

}
