package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-07 9:35
 */
public class Number464 {

    int n;
    int t;
    // 定义 f[statue][k] 为当前已被选择的数为 state，轮数为 k 时，「原始回合的先手」能否获胜（1 代表能，-1 代表不能）
    // 其中 k 从 0 开始，通过 k 的奇偶性可知是原始回合的先手还是后手
    int[][] f1 = new int[1 << 20][2];

    // 参考：https://leetcode.cn/problems/can-i-win/solution/by-ac_oier-0ed9/
    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {

        // 1：true -1：false
        n = maxChoosableInteger;
        t = desiredTotal;

        if (t == 0)
            return true;

        return dfs1(0, 0, 0) == 1;
    }

    // 递归函数表示当前状态为 state，tot 对应累计和，k 代表轮数，最终答案通过判断 dfs(0, 0, 0) 是否为 1 来得知
    // 转移过程中，如果发现当前回合的决策，能够直接使得累积和超过 t，说明当前回合玩家获胜
    // 或者如果当前决策能够导致下一回合的玩家失败的话，当前回合玩家也获胜，否则当前玩家失败
    private int dfs1(int state, int tot, int k) {

        // 使用一个二进制数 state 来表示 [1, n] 范围内的被选择的数的情况：二进制表示中 1 的位置代表数已被选择，否则代表尚未选择

        if (state == ((1 << n) - 1) && tot < t)
            return -1;

        if (f1[state][k % 2] != 0)
            return f1[state][k % 2];

        int hope = k % 2 == 0 ? 1 : -1;

        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1)
                continue;
            if (tot + i + 1 >= t)
                return f1[state][k % 2] = hope;
            if (dfs1(state | (1 << i), tot + i + 1, k + 1) == hope)
                return f1[state][k % 2] = hope;
        }

        return f1[state][k % 2] = -hope;
    }

    int[] f2 = new int[1 << 20];

    private int dfs2(int state, int tot) {

        if (f2[state] != 0)
            return f2[state];

        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1)
                continue;
            if (tot + i + 1 >= t)
                return f2[state] = 1;
            if (dfs2(state | (1 << i), tot + i + 1) == -1)
                return f2[state] = 1;
        }

        return f2[state] = -1;
    }

    // 参考：https://leetcode.cn/problems/can-i-win/solution/by-ac_oier-0ed9/
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {

        n = maxChoosableInteger;
        t = desiredTotal;

        if (n * (n + 1) / 2 < t)
            return false;

        if (t == 0)
            return true;

        return dfs2(0, 0) == 1;
    }

}
