package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-23 21:30
 */
public class Number1111 {

    // 参考：https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/solution/you-xiao-gua-hao-de-qian-tao-shen-du-by-leetcode-s/
    public int[] maxDepthAfterSplit(String seq) {

        int depth = 0;
        int length = seq.length();
        int[] ans = new int[length];

        for (int i = 0; i < length; i++) {
            if (seq.charAt(i) == '(') {
                ++depth;
                ans[i] = depth % 2;
            } else {
                ans[i] = depth % 2;
                --depth;
            }
        }

        return ans;
    }
}
