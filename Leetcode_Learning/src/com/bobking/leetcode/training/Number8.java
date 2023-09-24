package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

public class Number8 {

    // 参考：程序猿代码指南P256
    public int myAtoi1(String s) {

        if (s == null || s.length() == 0)
            return 0;

        s = s.trim();

        if (s.length() == 0)
            return 0;

        // 即不是以 +/- 或者数字开头的
        if (s.charAt(0) != '-' && s.charAt(0) != '+' && !Character.isDigit(s.charAt(0)))
            return 0;

        // 若以数字开头则角标从 0 开始，若以符号开头则角标从 1 开始
        int index = Character.isDigit(s.charAt(0)) ? 0 : 1;
        int res = 0;
        boolean positive = s.charAt(0) == '-' ? false : true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int cur = 0;

        // 因为对于整形类型的有符号数  最小值 = -2147483648  最大值 = 2147483647 所以一律转为负数
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            // 即当前符号的负数表示形式
            cur = '0' - s.charAt(index);
            // 溢出
            if ((res < minq) || (res == minq && cur < minr))
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            res = res * 10 + cur;
            index++;
        }

        if (positive && res == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;

        return positive ? -res : res;
    }

    public int myAtoi2(String s) {

        if (s == null || s.length() == 0)
            return 0;

        s = s.trim();

        if (s.length() == 0)
            return 0;

        if (s.charAt(0) != '-' && s.charAt(0) != '+' && !Character.isDigit(s.charAt(0)))
            return 0;

        int index = Character.isDigit(s.charAt(0)) ? 0 : 1;
        long res = 0L;
        boolean positive = s.charAt(0) == '-' ? false : true;

        while (index < s.length() && Character.isDigit(s.charAt(index))) {

            // res 一律以正数表示
            res = res * 10 + s.charAt(index) - '0';
            // 正数
            if (positive && res > Integer.MAX_VALUE) {
                res = Integer.MAX_VALUE;
                break;
            }
            // 负数
            if (!positive && res > 1L + Integer.MAX_VALUE) {
                res = 1L + Integer.MAX_VALUE;
                break;
            }
            index++;
        }

        return positive ? (int) res : (int) (-1 * res);
    }

    private class Automaton {

        // 定义四种状态: 0：" "(即空格)  1：+ / -  2：数字  3：字母
        private String state = "start";
        public long sum = 0;
        // 1:正 0:负
        public int positive = 1;

        private Map<String, String[]> map = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "number", "end"});
            put("signed", new String[]{"end", "end", "number", "end"});
            put("number", new String[]{"end", "end", "number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        public void get(char ch) {

            state = map.get(state)[getState(ch)];
            if ("number".equals(state)) {
                sum = sum * 10 + ch - '0';
                sum = positive == 1 ? Math.min(sum, (long) Integer.MAX_VALUE) : Math.min(sum, -1 * (long) Integer.MIN_VALUE);
            } else if ("signed".equals(state)) {
                positive = ch == '-' ? 0 : 1;
            }
        }

        public int getState(char ch) {

            if (ch == ' ') {
                return 0;
            } else if (ch == '+' || ch == '-') {
                return 1;
            } else if (Character.isDigit(ch)) {
                return 2;
            } else {
                return 3;
            }
        }
    }

    // 参考：https://leetcode-cn.com/problems/string-to-integer-atoi/solution/zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-leetcode-/
    public int myAtoi3(String s) {

        if (s == null || s.length() == 0)
            return 0;

        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++)
            automaton.get(s.charAt(i));

        return (int) (automaton.positive == 1 ? automaton.sum : -1 * automaton.sum);
    }


}
