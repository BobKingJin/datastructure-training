package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-20 21:02
 */
public class Number1370 {

    public String sortString(String s) {

        int[] bucket = new int[26];

        for (int i = 0; i < s.length(); i++)
            bucket[s.charAt(i) - 'a']++;

        char[] res = new char[s.length()];
        int index = 0;

        while (index < s.length()) {

            for (int i = 0; i < 26; i++) {
                if (bucket[i] != 0) {
                    res[index++] = (char) (i + 'a');
                    bucket[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (bucket[i] != 0) {
                    res[index++] = (char) (i + 'a');
                    bucket[i]--;
                }
            }
        }
        return new String(res);
    }
}
