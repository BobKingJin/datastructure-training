package com.bobking.leetcode.training;

public class Jianzhi05 {

    // 参考：程序猿代码指南P265
    public String replaceSpace1(String s) {

        if (s == null || s.length() == 0)
            return s;

        // 空格的数量
        int num = 0;
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ')
                num++;
        }

        char[] res = new char[s.length() + 2 * num];
        int index = res.length - 1;
        // 注意是从后往前，如果是从前往后，那么前面的会重复搬运
        for (int i = ch.length - 1; i >= 0; i--) {
            if (ch[i] != ' ') {
                res[index--] = ch[i];
            } else {
                res[index--] = '0';
                res[index--] = '2';
                res[index--] = '%';
            }
        }

        return new String(res);
    }

    // 参考：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/solution/mian-shi-ti-05-ti-huan-kong-ge-ji-jian-qing-xi-tu-/
    public String replaceSpace2(String s) {

        if (s == null || s.length() == 0)
            return s;

        StringBuilder res = new StringBuilder();

        for (Character c : s.toCharArray()) {
            if (c == ' ') {
                res.append("%20");
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }
}
