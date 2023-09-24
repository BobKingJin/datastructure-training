package com.bobking.leetcode.training;

public class Number151 {

    // 参考：程序猿代码指南P267
    // 额外解法参考：https://leetcode-cn.com/problems/reverse-words-in-a-string/solution/151-fan-zhuan-zi-fu-chuan-li-de-dan-ci-shuang-zh-2/
    public String reverseWords(String s) {

        if (s == null || s.length() == 0 || "".equals(s))
            return s;

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
                for (int index = l; index <= r; index++)
                    res.append(ch[index]);

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
}
