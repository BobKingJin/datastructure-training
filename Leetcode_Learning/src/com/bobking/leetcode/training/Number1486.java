package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-26 22:31
 */
public class Number1486 {

    public int xorOperation1(int n, int start) {

        int ans = start;

        for (int i = 1; i < n; i++) {
            int x = start + 2 * i;
            ans ^= x;
        }

        return ans;
    }

    private int calc(int x) {
        if (x % 4 == 0) {
            return x;
        } else if (x % 4 == 1) {
            return 1;
        } else if (x % 4 == 2) {
            return x + 1;
        } else {
            return 0;
        }
    }

    // 参考：https://leetcode.cn/problems/xor-operation-in-an-array/solution/gong-shui-san-xie-yi-ti-shuang-jie-mo-ni-dggg/
    public int xorOperation2(int n, int start) {
        // 整体除以 2，利用 %4 结论计算 ans 中除「最低一位」的结果
        int s = start >> 1;
        int prefix = calc(s - 1) ^ calc(s + n - 1);
        // 利用「奇偶性」计算 ans 中的「最低一位」结果
        int last = n & start & 1;
        int ans = prefix << 1 | last;
        return ans;
    }
}
