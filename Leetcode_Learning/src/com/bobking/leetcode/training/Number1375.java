package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-15 8:56
 */
public class Number1375 {

    // 参考：https://leetcode.cn/problems/number-of-times-binary-string-is-prefix-aligned/solution/qiao-miao-li-yong-xing-zhi-wei-hu-zui-da-79yx/
    public int numTimesAllBlue(int[] flips) {

        int ans = 0;
        int max = 0;
        int n = flips.length;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, flips[i]);
            if (max == i + 1)
                ans++;
        }
        return ans;
    }
}
