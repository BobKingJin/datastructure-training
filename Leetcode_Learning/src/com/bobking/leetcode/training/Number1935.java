package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-05-22 8:58
 */
public class Number1935 {

    public int canBeTypedWords(String text, String brokenLetters) {

        String[] words = text.split(" ");
        Set<Character> s = new HashSet<Character>();
        for (char ch: brokenLetters.toCharArray())
            s.add(ch);

        int ans = 0;
        for (String word: words) {
            boolean f = true;
            for (char ch: word.toCharArray()) {
                if (s.contains(ch)) {
                    f = false;
                    break;
                }
            }
            if (f)
                ++ans;
        }
        return ans;
    }
}
