package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-09-25 17:04
 */
public class Number2131 {

    // 参考：https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words/solution/lian-jie-liang-zi-mu-dan-ci-de-dao-de-zu-vs99/
    public int longestPalindrome1(String[] words) {

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++)
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);

        int add = 0;
        int ans = 0;

        for (String s : map.keySet()) {
            if (s.charAt(0) == s.charAt(1)) {
                // 这个位置之所以先左移一位，然后右移两位，是因为 map.get(s) 可能为奇数
                ans += (map.get(s) >> 1) << 2;
                // 可以作为中心单词的、出现奇数次的回文单词
                if ((map.get(s) & 1) == 1)
                    add = 2;
            } else {
                String t = pal(s);
                // 之所以不一次 * 4，是因为比如 cl gg lc，---->>>> map.get("cl") * 2 + map("lc") * 2，即 "cl" 和 "lc" 是分开算的
                if (map.containsKey(t))
                    ans += Math.min(map.get(s), map.get(t)) * 2;
            }
        }

        return ans + add;
    }

    private String pal(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    int count[][] = new int[26][26];

    // 参考：https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words/solution/lian-jie-liang-zi-mu-dan-ci-de-dao-de-zu-vs99/
    public int longestPalindrome2(String[] words) {

        for (int i = 0; i < words.length; i++)
            count[words[i].charAt(0) - 'a'][words[i].charAt(1) - 'a']++;

        int add = 0;
        int ans = 0;

        for (int i = 0; i < 26; i++) {
            for (int j = i; j < 26; j++) {
                if (i == j) {
                    ans += (count[i][i] >> 1) << 2;
                    if ((count[i][i] & 1) == 1)
                        add = 2;
                } else {
                    ans += Math.min(count[i][j], count[j][i]) << 2;
                }
            }
        }
        return ans + add;
    }

}
