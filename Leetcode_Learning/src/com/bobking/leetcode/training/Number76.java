package com.bobking.leetcode.training;

public class Number76 {

    // 参考：程序猿代码指南P310
    public String minWindow(String s, String t) {

        if (s == null || t == null || s.length() < t.length())
            return "";

        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();

        int[] map = new int[256];
        for (int i = 0; i < ch2.length; i++)
            map[ch2[i]]++;

        int left = 0;
        int right = 0;
        int match = ch2.length;
        int minLen = Integer.MAX_VALUE;
        String res = "";

        while (right != ch1.length) {
            map[ch1[right]]--;
            // 减完之后大于零，说明减之前大于零，那么此时 match 应该减一
            if (map[ch1[right]] >= 0)
                match--;
            if (match == 0) {
                while (map[ch1[left]] < 0)
                    map[ch1[left++]]++;

                int len = right - left + 1;
                minLen = Math.min(minLen, len);
                if (minLen == len)
                    res = s.substring(left, right + 1);

                match++;
                map[ch1[left++]]++;
            }
            right++;
        }

        return res;
    }
}
