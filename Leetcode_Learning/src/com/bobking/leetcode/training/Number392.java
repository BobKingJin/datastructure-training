package com.bobking.leetcode.training;

public class Number392 {

    public boolean isSubsequence(String s, String t) {

        if (s == null || t == null || s.length() > t.length())
            return false;
        if (s == "")
            return true;

        int sIndex = 0;
        int tIndex = 0;

        while (tIndex < t.length()) {

            if (s.charAt(sIndex) == t.charAt(tIndex)) {

                sIndex++;
                if (sIndex == s.length())
                    return true;
            }

            tIndex++;
        }

        return false;
    }
}
