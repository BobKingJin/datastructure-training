package com.bobking.leetcode.training;

public class LCP19 {

    // 参考：https://leetcode.cn/problems/UlBDOe/solution/qiu-xie-shou-cang-ji-by-leetcode-solution/
    public int minimumOperations(String leaves) {

        // 由于想要将收藏集中树叶的排列调整成「红、黄、红」三部分
        // 因此可以用 3 个状态分别表示其中的每一部分，即状态 0 和状态 2 分别表示前面和后面的红色部分，状态 1 表示黄色部分
        // 用 f[i][j] 表示对于第 0 片到第 i 片叶子（记为 leaves[0..i]）进行调整操作，并且第 i 片叶子处于状态 j 时的最小操作次数
        // 当 j = 0 时，需要将第 i 片叶子变成红色，并且第 i - 1 片叶子也只能处于 j = 0 的状态（因为没有编号更小的状态了）
        // 因此有状态转移方程：f[i][0] = f[i - 1][0] + isYellow(i)，其中 isYellow(i) 为示性函数，当第 i 片叶子为黄色时为 1，红色时为 0
        // 当 j = 1 时，需要将第 i 片叶子变成黄色，而第 i - 1 片叶子既可以处于 j = 1 的状态，也可以处于 j = 0 的状态
        // 选择其中的较小值，因此有状态转移方程：f[i][1] = min{f[i − 1][0], f[i − 1][1]} + isRed(i)，其中 isRed(i) 为示性函数，当第 i 片叶子为红色时为 1，黄色时为 0
        // 当 j = 2 时，需要将第 i 片叶子变成红色，而第 i - 1 片叶子既可以处于 j = 2 的状态，也可以处于 j = 1 的状态
        // （注意这里不能处于 j = 0 的状态，因为每一种状态包含的叶子数量必须至少为 1），选择其中的较小值
        // 因此有状态转移方程：f[i][2] = min{f[i − 1][1], f[i − 1][2]} + isYellow(i)
        // 最终的答案即为 f[n - 1][2]，其中 n 是字符串 leaves 的长度，也就是树叶的总数

        // 由于因为每一种状态包含的叶子数量必须至少为 1，因此不仅需要特别注意 j = 2 时的状态转移方程
        // 还需要考虑每个状态本身是否是符合要求的。对于状态 f[i][j] 而言，它包含了 leaves[0..i] 共 i + 1 片叶子以及 j + 1 个状态
        // 因此叶子的数量必须大于等于状态的数量，即满足 i ≥ j。这样一来，所有 i < j 的状态 f[i][j] 都是不符合要求的
        // 观察上面的状态转移方程，在每一步转移时都是取最小值，因此可以将所有不符合要求的状态置为一个极大值（例如 n + 1 或整数类型的上限等）
        // 同时需要注意的是，当 i = 0 时，f[i][..] 会从 f[i - 1][..] 转移而来，但后者是没有意义的，因此需要对 f[i][..] 进行特殊处理
        // 由于当 i = 0 时，j 也只能为 0，因此有：f[0][0] = isYellow(0) 作为唯一的边界条件

        int n = leaves.length();
        int[][] f = new int[n][3];
        f[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        f[0][1] = f[0][2] = f[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < n; ++i) {
            int isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            f[i][0] = f[i - 1][0] + isYellow;
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + isRed;
            if (i >= 2)
                f[i][2] = Math.min(f[i - 1][1], f[i - 1][2]) + isYellow;
        }

        return f[n - 1][2];
    }
}
