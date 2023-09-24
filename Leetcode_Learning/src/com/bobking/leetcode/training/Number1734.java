package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-03 7:33
 */
public class Number1734 {

    // 参考：https://leetcode.cn/problems/decode-xored-permutation/solution/gong-shui-san-xie-note-bie-pian-li-yong-zeh6o/
    public int[] decode(int[] encoded) {

        int n = encoded.length + 1;
        int[] ans = new int[n];

        // 求得除了 ans[n - 1] 的所有异或结果
        int a = 0;
        for (int i = 0; i < n - 1; i += 2)
            a ^= encoded[i];

        // 求得 ans 的所有异或结果
        int b = 0;
        for (int i = 1; i <= n; i++)
            b ^= i;

        // 求得 ans[n - 1] 后，从后往前做
        ans[n - 1] = a ^ b;

        for (int i = n - 2; i >= 0; i--)
            ans[i] = encoded[i] ^ ans[i + 1];

        return ans;
    }
}
