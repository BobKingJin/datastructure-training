package com.bobking.leetcode.training;

public class Number151 {

    // 参考：程序猿代码指南P267
    // 额外解法参考：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/151-fan-zhuan-zi-fu-chuan-li-de-dan-ci-shuang-zh-2/
    public String reverseWords1(String s) {

        if (s == null || s.length() == 0 || "".equals(s)) {
            return s;
        }

        StringBuilder res = new StringBuilder();
        char[] ch = s.toCharArray();
        reverse(ch, 0, ch.length - 1);

        int l = -1;
        int r = -1;

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != ' ') {
                l = (i == 0 || ch[i - 1] == ' ') ? i : l;
                r = (i == ch.length - 1 || ch[i + 1] == ' ') ? i : r;
            }
            if (l != -1 && r != -1) {
                reverse(ch, l, r);
                for (int index = l; index <= r; index++) {
                    res.append(ch[index]);
                }
                res.append(' ');
                l = -1;
                r = -1;
            }
        }
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    private void reverse(char[] ch, int start, int end) {
        char temp = 0;
        while (start < end) {
            temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }

    public String reverseWords2(String s) {
        s = s.trim();
        // j 为 右端点不为空字符串的角标
        int j = s.length() - 1;
        // i 为 左端点
        int i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            // i 已经指向空字符串, 因此需要 i + 1
            res.append(s.substring(i + 1, j + 1) + " ");
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            // 此时 i 已经指向非空字符串
            j = i;
        }
        return res.toString().trim();
    }

    public String reverseWords3(String s) {
        String[] strs = s.trim().split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) {
                continue;
            }
            res.append(strs[i] + " ");
        }
        return res.toString().trim();
    }
}
