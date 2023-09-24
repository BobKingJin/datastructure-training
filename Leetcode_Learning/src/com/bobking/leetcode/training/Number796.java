package com.bobking.leetcode.training;

public class Number796 {

    // 参考：程序猿代码指南P254
    public boolean rotateString1(String s, String goal) {

        if (s == null || goal == null)
            return false;

        return s.length() == goal.length() && (s + s).contains(goal);
    }

    // 参考：https://leetcode-cn.com/problems/rotate-string/solution/xuan-zhuan-zi-fu-chuan-by-leetcode/
    public boolean rotateString2(String s, String goal) {

        if (s.length() != goal.length())
            return false;

        if (s.length() == 0)
            return true;

        search:
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt((i + j) % s.length()) != goal.charAt(j))
                    continue search;
            }
            return true;
        }
        return false;
    }


}
