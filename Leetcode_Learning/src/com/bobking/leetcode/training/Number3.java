package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Number3 {

    // 参考：程序猿代码指南P301
    public int lengthOfLongestSubstring1(String s) {

        if (s == null || s.length() == 0 || s.equals(""))
            return 0;

        int[] map = new int[256];
        // map 用于记录每一个字符上一次出现的位置(即最近出现的位置)
        for (int i = 0; i < map.length; i++)
            // 每个角标都初始化为 -1
            map[i] = -1;

        char[] ch = s.toCharArray();
        // 前一个无重复子串的开始位置前一个角标
        // 以 str[i - 1]字符结尾的情况下，最长无重复字符子串开始位置的前一个位置
        int pre = -1;
        int len = 0;
        int res = 0;

        for (int i = 0; i < ch.length; i++) {
            pre = Math.max(pre, map[ch[i]]);
            len = i - pre;
            res = Math.max(res, len);
            map[ch[i]] = i;
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
    // 同解法1
    public int lengthOfLongestSubstring2(String s) {

        if (s == null || s.length() == 0 || s.equals(""))
            return 0;

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int res = 0;
        int left = 0;

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)))
                left = Math.max(left, map.get(s.charAt(i)) + 1);

            map.put(s.charAt(i), i);
            res = Math.max(res, i - left + 1);
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
    public int lengthOfLongestSubstring3(String s) {

        if (s == null || s.length() == 0 || s.equals(""))
            return 0;

        // 哈希集合，记录每个字符是否出现过
        Set<Character> set = new HashSet<Character>();
        int n = s.length();
        // i 为左指针
        // 右指针，初始值为 -1，相当于在字符串的左边界的左侧，还没有开始移动
        // 双指针：i - end 范围内
        // 之所以右指针 end 不用重置是因为如果依次递增地枚举子串的起始位置，那么子串的结束位置也是递增的
        // 假设选择字符串中的第 k 个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 r_k
        // 那么当选择第 k + 1 个字符作为起始位置时，首先从 k + 1 到 r_k的字符显然是不重复的
        // 并且由于少了原本的第 k 个字符，可以尝试继续增大 r_k直到右侧出现了重复字符为止
        int end = -1;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0)
                // 左指针向右移动一格，移除一个字符
                set.remove(s.charAt(i - 1));
            while (end + 1 < n && !set.contains(s.charAt(end + 1))) {
                set.add(s.charAt(end + 1));
                end++;
            }
            res = Math.max(res, end - i + 1);
        }
        return res;
    }
}
