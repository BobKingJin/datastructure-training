package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @author BobKing
 * @create 2022-06-25 22:12
 */
public class Number1209 {

    // 参考：https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xiang-lin--4/
    public String removeDuplicates1(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
        // 首先初始化 length 为一个 sb 不可能达到的值
        int length = -1;
        // 当 length == sb.length() 时，即没有删除任何字符，结束递归
        while (length != sb.length()) {
            length = sb.length();
            for (int i = 0, count = 1; i < sb.length(); ++i) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count = 1;
                } else if (++count == k) {
                    sb.delete(i - k + 1, i + 1);
                    break;
                }
            }
        }
        return sb.toString();
    }

    // 参考：https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xiang-lin--4/
    public String removeDuplicates2(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
        int count[] = new int[sb.length()];

        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }

    // 参考：https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xiang-lin--4/
    public String removeDuplicates3(String s, int k) {

        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> counts = new Stack<Integer>();

        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.push(1);
            } else {
                int incremented = counts.pop() + 1;
                if (incremented == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                } else {
                    counts.push(incremented);
                }
            }
        }
        return sb.toString();
    }

}
