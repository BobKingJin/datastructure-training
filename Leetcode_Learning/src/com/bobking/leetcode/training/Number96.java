package com.bobking.leetcode.training;

public class Number96 {

    // 参考：程序猿代码指南P173
    public int numTrees(int n) {

        if (n < 0) {
            return 0;
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        int[] num = new int[n + 1];
        num[0] = 1;
        // num[n] 依赖于 nums[0] ... num[n - 1]，所以要先算出 nums[0] ... num[n - 1]
        for (int j = 1; j <= n; j++) {
            // 有 j 个节点，然后分别以 1 ~ i 为头节点计算出 num[j]
            for (int i = 1; i <= j; i++) {
                num[j] += num[i - 1] * num[j - i];
            }
        }

        return num[n];
    }
}
