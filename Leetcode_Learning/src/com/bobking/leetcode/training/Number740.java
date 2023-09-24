package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-30 21:41
 */
public class Number740 {

    int[] cnts = new int[10010];

    // 参考：https://leetcode.cn/problems/delete-and-earn/solution/gong-shui-san-xie-zhuan-huan-wei-xu-lie-6c9t0/
    public int deleteAndEarn(int[] nums) {

        // 定义 f[i][0] 代表数值为 i 的数字「不选择」的最大价值
        //     f[i][1] 代表数值为 i 的数字「选择」的最大价值
        // 为了方便，可以先对 nums 中出现的所有数值进行计数，而且由于数据范围只有 10^4
        // 可以直接使用数组 cnts[] 进行计数：cnts[x] = i 代表数值 x 出现了 i 次
        // 然后分别考虑一般性的 f[i][0] 和 f[i][1] 该如何计算：
        // f[i][0]：当数值 i 不被选择，那么前一个数「可选/可不选」，在两者中取 max 即可。转移方程为 f[i][0] = max(f[i − 1][0], f[i − 1][1])
        // f[i][1]：当数值 i 被选，那么前一个数只能「不选」，同时为了总和最大数值 i 要选就全部选完。转移方程为 f[i][1] = f[i - 1][0] + i * cnts[i]

        int n = nums.length;
        int max = 0;
        for (int x : nums) {
            cnts[x]++;
            max = Math.max(max, x);
        }

        // f[i][0] 代表「不选」数值 i
        // f[i][1] 代表「选择」数值 i
        int[][] f = new int[max + 1][2];
        for (int i = 1; i <= max; i++) {
            f[i][1] = f[i - 1][0] + i * cnts[i];
            f[i][0] = Math.max(f[i - 1][1], f[i - 1][0]);
        }

        return Math.max(f[max][0], f[max][1]);
    }
}
