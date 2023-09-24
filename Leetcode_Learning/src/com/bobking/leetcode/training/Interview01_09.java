package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-26 7:05
 */
public class Interview01_09 {

    int N = 200010;
    int P = 13131;
    int[] h = new int[N];
    int[] p = new int[N];

    // 参考：https://leetcode.cn/problems/string-rotation-lcci/solution/by-ac_oier-2wo1/
    public boolean isFlipedString(String s1, String s2) {

        // 若两字符串互为旋转，则「其一字符串」必然为「另一字符串拓展两倍长度后（循环子串）」的子串。
        // 基于此，可以使用「字符串哈希」进行求解：
        // 先计算 s2 的字符串哈希值 t，然后构造出 s1 + s1 的哈希数组和次方数组
        // 两数组中检查是否存在长度为 n 的连续子段的哈希值 cur 与 t 相等

        if (s1.length() != s2.length())
            return false;

        int n = s1.length();
        for (int i = 1; i <= n; i++)
            h[i] = h[i - 1] * P + s2.charAt(i - 1);
        int t = h[n];
        s1 = s1 + s1;
        p[0] = 1;

        for (int i = 1; i <= 2 * n; i++) {
            h[i] = h[i - 1] * P + s1.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }

        for (int i = 1; i + n - 1 <= 2 * n; i++) {
            int j = i + n - 1;
            int cur = h[j] - h[i - 1] * p[j - i + 1];
            if (cur == t)
                return true;
        }
        return false;
    }
}
