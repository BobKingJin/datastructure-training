package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2021-05-04 12:01
 */
public class Number205 {

    public boolean isIsomorphic1(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0)
            return false;

        if (s.length() != t.length())
            return false;

        // 建立两两映射
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();

        for (int i = 0; i < s.length(); i++) {

            char x = s.charAt(i);
            char y = t.charAt(i);

            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x))
                return false;

            s2t.put(x, y);
            t2s.put(y, x);
        }

        return true;
    }

    // 参考:https://leetcode-cn.com/problems/isomorphic-strings/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-42/
    public boolean isIsomorphic2(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0)
            return false;

        if (s.length() != t.length())
            return false;

        return isIsomorphicHelper1(s).equals(isIsomorphicHelper1(t));
    }

    private String isIsomorphicHelper1(String s) {

        int[] map = new int[128];
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            // 当前字母第一次出现，赋值
            if (map[c] == 0) {
                map[c] = count;
                count++;
            }
            sb.append(map[c]);
        }

        return sb.toString();
    }

    public boolean isIsomorphic3(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0)
            return false;

        if (s.length() != t.length())
            return false;

        return isIsomorphicHelper2(s).equals(isIsomorphicHelper2(t));
    }

    private String isIsomorphicHelper2(String s) {

        int[] map = new int[128];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            // 当前字母第一次出现,赋值
            if (map[c] == 0)
                map[c] = i + 1;
            sb.append(map[c]);
        }

        return sb.toString();
    }

    public boolean isIsomorphic4(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0)
            return false;

        if (s.length() != t.length())
            return false;

        int[] sMap = new int[128];
        int[] tMap = new int[128];

        for (int i = 0; i < s.length(); i++) {

            char x = s.charAt(i);
            char y = t.charAt(i);

            if (sMap[x] != tMap[y]) {
                return false;
            } else {

                if (sMap[x] == 0) {
                    sMap[x] = i + 1;
                    tMap[y] = i + 1;
                }
            }
        }

        return true;
    }

    // 对比每个字符在字符串第一次出现的位置是否相同
    private boolean isIsomorphic5(String s, String t) {

        if (s == null || s.length() == 0 || t == null || t.length() == 0)
            return false;

        if (s.length() != t.length())
            return false;

        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(ss[i]) != t.indexOf(tt[i]))
                return false;
        }

        return true;
    }

}
