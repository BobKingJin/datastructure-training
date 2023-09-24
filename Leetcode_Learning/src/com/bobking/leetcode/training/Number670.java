package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-11 22:39
 */
public class Number670 {

    // 参考：https://leetcode.cn/problems/maximum-swap/solution/0ms-100-bu-miao-da-wo-by-wang-xue-lei-2-iyz9/
    public int maximumSwap(int num) {

        char[] chars = Integer.toString(num).toCharArray();
        int maxIdx = chars.length - 1;
        int[] maxArr = new int[chars.length];

        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] > chars[maxIdx])
                maxIdx = i;

            maxArr[i] = maxIdx;
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[maxArr[i]] != chars[i]) {
                char temp = chars[maxArr[i]];
                chars[maxArr[i]] = chars[i];
                chars[i] = temp;
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }
}
