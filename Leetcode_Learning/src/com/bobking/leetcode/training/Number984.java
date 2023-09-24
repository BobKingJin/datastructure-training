package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-19 11:26
 */
public class Number984 {

    // 参考：https://leetcode.cn/problems/string-without-aaa-or-bbb/solution/mei-ci-cha-ru-de-xing-shi-shi-aabhuo-zhe-bbahuo-zh/
    public String strWithout3a3b(int a, int b) {

        StringBuilder res = new StringBuilder();

        while (a > 0 && b > 0) {
            if (a > b) {
                res.append("aab");
                a -= 2;
                b--;
            } else if (a == b) {
                res.append("ab");
                a--;
                b--;
            } else if (a < b) {
                res.append("bba");
                a--;
                b -= 2;
            }
        }
        while (a > 0) {
            res.append('a');
            a--;
        }
        while (b > 0) {
            res.append('b');
            b--;
        }
        return res.toString();
    }
}
