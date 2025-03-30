package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @Date: 2025/3/30 10:14
 * @Author: BobKing
 * @Description:
 */
public class Number3306 {

    private final String VOWELS_STR = "aeiou";

    public long countOfSubstrings(String word, int k) {
        char[] s = word.toCharArray();
        return f(s, k) - f(s, k + 1);
    }

    private long f(char[] word, int k) {
        long ans = 0;
        // 这里用哈希表实现，替换成数组会更快
        // 每种元音的个数
        HashMap<Character, Integer> cnt1 = new HashMap<Character, Integer>();
        // 辅音个数
        int cnt2 = 0;
        int left = 0;
        for (char b : word) {
            if (VOWELS_STR.indexOf(b) >= 0) {
                cnt1.merge(b, 1, Integer::sum);
            } else {
                cnt2++;
            }
            while (cnt1.size() == 5 && cnt2 >= k) {
                char out = word[left];
                if (VOWELS_STR.indexOf(out) >= 0) {
                    if (cnt1.merge(out, -1, Integer::sum) == 0) {
                        cnt1.remove(out);
                    }
                } else {
                    cnt2--;
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }

}
