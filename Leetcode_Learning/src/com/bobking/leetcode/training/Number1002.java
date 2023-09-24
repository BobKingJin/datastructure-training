package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-05-15 8:10
 */
public class Number1002 {

    public List<String> commonChars(String[] words) {
        
        List<String> res = new ArrayList<String>();

        if (words.length == 0)
            return res;
        // 用来统计所有字符串里字符出现的最小频率
        int[] hash= new int[26];
        for (int i = 0; i < words[0].length(); i++) // 用第一个字符串给 hash 初始化
            hash[words[0].charAt(i)- 'a']++;

        // 统计除第一个字符串外字符的出现频率
        for (int i = 1; i < words.length; i++) {
            int[] hashOtherStr= new int[26];
            for (int j = 0; j < words[i].length(); j++)
                hashOtherStr[words[i].charAt(j)- 'a']++;
            // 更新 hash，保证 hash 里统计 26 个字符在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++)
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
        }

        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) {
                char c= (char) (i+'a');
                res.add(String.valueOf(c));
                hash[i]--;
            }
        }

        return res;
    }
}
