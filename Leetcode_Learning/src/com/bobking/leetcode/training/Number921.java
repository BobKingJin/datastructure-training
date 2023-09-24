package com.bobking.leetcode.training;

public class Number921 {

    // 参考：程序猿代码指南P290
    // 参考：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/solution/shi-gua-hao-you-xiao-de-zui-shao-tian-jia-by-leetc/
    public int minAddToMakeValid(String s) {

        if (s == null || s.length() == 0 || "".equals(s))
            return 0;

        char[] chas = s.toCharArray();
        int status = 0;
        int res = 0;

        for (int i = 0; i < chas.length; i++) {

            if (chas[i] == '(') {
                status++;
            } else {
                status--;
            }
            // 此时缺少 (，那么就得补上一个 (
            if (status == -1) {
                // res++意味着补上了一个 (，补上了意味着此时不差 (，那么status++
                res++;
                status++;
            }
        }
        // 有可能是缺少 )，所以需要加上status
        return res + status;
    }
}
