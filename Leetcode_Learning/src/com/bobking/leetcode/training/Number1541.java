package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-08 15:19
 */
public class Number1541 {

    // 参考：https://leetcode.cn/problems/minimum-insertions-to-balance-a-parentheses-string/solution/si-lu-jiu-shi-jian-dan-de-zuo-you-pi-pei-by-jiang-/
    public int minInsertions(String s) {

        // 右括号是左括号的两倍且需保证左括号 '(' 必须在对应的连续两个右括号 '))' 之前
        // 用来存储左括号的数量
        int depth = 0;
        int res = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(')
                depth++;
            else {
                // 判断下一个是否也是右括号，是则 i++，下一轮循环判断下下个数
                if (i + 1 < len && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // 如果不是，则意味着不管前边有没有左括号，此时都需要补上一个右括号
                    res++;
                }
                // 对前面有没有可以进行匹配的左括号判断
                // 注意前面的操作已经完成是否需要添加右括号的判断
                // 如果有可以进行匹配的左括号，且当前为右括号，此时需要 depth - 1
                // 减一操作并不代表一定完成一个左括号与两个右括号的匹配，只有当下一个还是右括号时才有此意
                // 如果左括号比右括号多则 depth 不会减到 0，不管 depth 是否为 0，最后都会对 depth 进行处理
                if (depth > 0) {
                    depth--;
                } else {
                    // 若没有，则可以当做前面没有任何左括号
                    // 而前面已经完成是否补齐右括号的判断与操作，因此此时只需要补上左括号即可
                    res++;
                }
            }
        }
        // depth表示还有 depth个 左括号未完成平衡
        res += depth * 2;
        return res;
    }
}
