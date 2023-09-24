package com.bobking.leetcode.training;

public class Number96 {

    // 参考：程序猿代码指南P173
    public int numTrees(int n) {

        if (n < 0)
            return 0;

        if (n == 0 || n == 1)
            return 1;

        int[] num = new int[n + 1];
        num[0] = 1;
        // num[n] 依赖于 nums[0] ... num[n - 1]，所以要先算出 nums[0] ... num[n - 1]
        for (int i = 1; i <= n; i++) {
            // 有 i 个节点，然后分别以 1 - i 为头节点计算出 num[i]
            for (int j = 1; j <= i; j++)
                num[i] += num[j - 1] * num[i - j];
        }

        return num[n];
    }
}
