package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-06-24 7:52
 */
public class Number1297 {

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i <= s.length() - minSize; i++) {

            String str = s.substring(i, i + minSize);
            // 检查 str中不同字母小于等于maxLetters
            Set<Character> set = new HashSet<Character>();
            for (char ch : str.toCharArray())
                set.add(ch);
            if (set.size() <= maxLetters)
                map.put(str, map.getOrDefault(str, 0) + 1);
        }
        // 取出子字符串出现的最大次数
        int ans = 0;
        for (String st : map.keySet())
            ans = Math.max(ans, map.get(st));

        return ans;
    }
}
