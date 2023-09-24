package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-30 15:23
 */
public class Interview01_05 {

    // 参考：https://leetcode.cn/problems/one-away-lcci/solution/by-ac_oier-7ml0/
    public boolean oneEditAway(String first, String second) {

        int n = first.length();
        int m = second.length();

        if (Math.abs(n - m) > 1)
            return false;

        if (n > m)
            return oneEditAway(second, first);

        int i = 0;
        int j = 0;
        int cnt = 0;

        while (i < n && j < m && cnt <= 1) {
            char c1 = first.charAt(i);
            char c2 = second.charAt(j);
            if (c1 == c2) {
                i++;
                j++;
            } else {
                // 若 n = m，说明此时只能通过「替换」操作消除不同，分别让 i 和 j 后移，并对 cnt 进行加一操作
                if (n == m) {
                    i++;
                    j++;
                    cnt++;
                } else {
                    // 若 n != m，由于人为确保了 first 更短，即此时是 n < m，此时只能通过对 first 字符串进行「添加」操作来消除不同
                    // 此时让 j 后移，i 不动（含义为在 first 字符串中的 i 位置增加一个 b[j] 字符），并对 cnt 进行加一操作
                    j++;
                    cnt++;
                }
            }
        }
        return cnt <= 1;
    }
}
