package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-05-09 15:42
 */
public class Number316 {

    // 参考：程序猿代码指南P276
    public String removeDuplicateLetters1(String s) {

        if (s == null || s.length() == 0 || "".equals(s))
            return s;

        char[] ch = s.toCharArray();
        // 小写字母 ASCII码 值的范围为 [97 ~ 122]，所以用长度为 26 的数组做词频统计
        int[] map = new int[26];
        // 统计词频
        for (int i = 0; i < s.length(); i++)
            map[ch[i] - 'a']++;

        char[] res = new char[26];
        int L = 0;
        int R = 0;
        int index = 0;

        while (R != s.length()) {

            // 后面已经选取的位置要置为 -1 不允许再次被挑选
            if (map[ch[R] - 'a'] == -1 || --map[ch[R] - 'a'] > 0) {
                R++;
            } else {
                int pick = -1;
                for (int i = L; i <= R; i++) {
                    // 选取 L - R 中字典序最小的字符
                    if (map[ch[i] - 'a'] != -1 && (pick == -1 || ch[i] < ch[pick]))
                        pick = i;
                }

                res[index++] = ch[pick];
                // 将 pick + 1 - R 范围内的数增加回去
                for (int i = pick + 1; i <= R; i++) {
                    if (map[ch[i] - 'a'] != -1)
                        map[ch[i] - 'a']++;
                }

                // 将已选取的位置的值置为 -1
                map[ch[pick] - 'a'] = -1;
                L = pick + 1;
                R = L;
            }
        }

        return String.valueOf(res, 0, index);
    }

    // 参考：https://leetcode-cn.com/problems/remove-duplicate-letters/solution/you-qian-ru-shen-dan-diao-zhan-si-lu-qu-chu-zhong-/
    // 参考：https://leetcode-cn.com/problems/remove-duplicate-letters/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-4/
    // 参考：https://leetcode-cn.com/problems/remove-duplicate-letters/solution/qu-chu-zhong-fu-zi-mu-by-leetcode-soluti-vuso/
    public String removeDuplicateLetters2(String s) {

        if (s == null || s.length() == 0 || "".equals(s))
            return s;

        char[] ch = s.toCharArray();
        boolean[] isNotInStack = new boolean[26];
        int[] num = new int[26];
        // 统计词频
        for (int i = 0; i < s.length(); i++)
            num[ch[i] - 'a']++;

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < ch.length; i++) {

            if (!isNotInStack[ch[i] - 'a']) {
                while (res.length() > 0 && res.charAt(res.length() - 1) > ch[i]) {
                    if (num[res.charAt(res.length() - 1) - 'a'] > 0) {
                        isNotInStack[res.charAt(res.length() - 1) - 'a'] = false;
                        res.deleteCharAt(res.length() - 1);
                    } else {
                        break;
                    }
                }

                isNotInStack[ch[i] - 'a'] = true;
                res.append(ch[i]);
            }
            num[ch[i] - 'a']--;
        }

        return res.toString();
    }
}
