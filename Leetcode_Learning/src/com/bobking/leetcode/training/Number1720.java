package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-17 19:23
 */
public class Number1720 {

    // 参考：https://leetcode.cn/problems/decode-xored-array/solution/gong-shui-san-xie-li-yong-yi-huo-xing-zh-p1bi/
    public int[] decode(int[] encoded, int first) {

        // encoded[i-1] XOR arr[i-1] = arr[i]

        int n = encoded.length + 1;
        int[] ans = new int[n];
        ans[0] = first;

        for (int i = 1; i < n; i++)
            ans[i] = ans[i - 1] ^ encoded[i - 1];

        return ans;
    }
}
