package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author BobKing
 * @create 2022-09-27 9:29
 */
public class Interview01_02 {

    public boolean CheckPermutation1(String s1, String s2) {
        char[] c1=s1.toCharArray();
        Arrays.sort(c1);
        char[] c2=s2.toCharArray();
        Arrays.sort(c2);
        return new String(c1).equals(new String(c2));
    }

    public boolean CheckPermutation2(String s1, String s2) {

        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 != len2)
            return false;

        HashMap<Character, Integer> dic = new HashMap<Character, Integer>();
        for (int i = 0; i < len1; i++)
            dic.put(s1.charAt(i) , dic.getOrDefault(s1.charAt(i), 0) + 1);

        for (int i = 0; i < len2; i++)
            dic.put(s2.charAt(i) , dic.getOrDefault(s2.charAt(i), 0) - 1);

        for (int val : dic.values()) {
            if (val != 0)
                return false;
        }
        return true;
    }

    public boolean CheckPermutation3(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int tot = 0;

        if (n != m)
            return false;

        int[] cnts = new int[128];

        for (int i = 0; i < n; i++) {
            if (++cnts[s1.charAt(i)] == 1)
                tot++;
            if (--cnts[s2.charAt(i)] == 0)
                tot--;
        }
        return tot == 0;
    }
}
