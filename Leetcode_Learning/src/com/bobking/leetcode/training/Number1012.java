package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-03-20 23:20
 */
public class Number1012 {

    char[] s;
    int[][] dp;

    // 参考：https://leetcode.cn/problems/numbers-with-repeated-digits/solution/by-endlesscheng-c5vg/
    public int numDupDigitsAtMostN(int n) {

        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][1 << 10];
        // 初始化dp
        for (int i = 0; i < m; i++)
            // -1 表示没有计算过
            Arrays.fill(dp[i], -1);
        // 先算出 1 - n之间没有重复数字的个数 a
        // 这里 isLimit 为什么要初始化为 true，如果是 false，那马上要填的这个填 0 ~ 9 就都可以了，不受 [1, n] 的约束，显然不对
        return n - f(0, 0, true, false);
    }

    // 定义 f(i, mask, isLimit, isNum) 表示构造第 i 位及其之后数位的合法方案数
    // mask 表示前面选过的数字集合，换句话说，第 i 位要选的数字不能在 mask 中
    // isLimit 表示当前是否受到了 n 的约束（注意要构造的数字不能超过 n）。若为真，则第 i 位填入的数字至多为 s[i]，否则可以是 9
    // 如果在受到约束的情况下填了 s[i]，那么后续填入的数字仍会受到 n 的约束。例如 n = 123，那么 i = 0 填的是 1 的话，i = 1 的这一位至多填 2
    // isNum 表示 i 前面的数位是否填了数字。若为假，则当前位可以跳过（不填数字），或者要填入的数字至少为 1
    // 若为真，则要填入的数字可以从 0 开始。例如 n = 123，在 i = 0 时跳过的话，相当于后面要构造的是一个 99 以内的数字了，如果 i = 1 不跳过，那么相当于构造一个 10 到 99 的两位数，
    // 如果 i = 1 跳过，相当于构造的是一个 9 以内的数字
    private int f(int i, int mask, boolean isLimit, boolean isNum) {

        if (i == s.length)
            // 之前填过数字, 那么就有一个合法数字了
            return isNum ? 1 : 0;

        // 前面既没有填 n 对应位上的数，又填了数，那么后面填的都不受限
        if (!isLimit && isNum && dp[i][mask] != -1)
            return dp[i][mask];

        int ans = 0;

        // 可以选择跳过当前位, 也不填数字
        if (!isNum)
            // 为什么这里 isLimit 也是 false: 之前都没填数字，选择跳过当前位，下一位 i + 1 位当然也可以从 0 ~ 9 选填
            ans = f(i + 1, mask, false, false);
        int up = isLimit ? s[i] - '0' : 9;
        for (int d = isNum ? 0 : 1; d <= up; d++) {
            if ((mask >> d & 1) == 0)
                ans += f(i + 1, mask | (1 << d), isLimit && d == up, true);
        }
        if (!isLimit && isNum)
            dp[i][mask] = ans;
        return ans;
    }
}
