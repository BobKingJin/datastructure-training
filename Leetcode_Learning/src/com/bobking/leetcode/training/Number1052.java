package com.bobking.leetcode.training;

public class Number1052 {

    // 参考：https://leetcode.cn/problems/grumpy-bookstore-owner/solution/hua-dong-chuang-kou-luo-ti-by-ac_oier-nunu/
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {

        // 由于「技巧」只会将情绪将「生气」变为「不生气」，不生气仍然是不生气
        // 可以先将原本就满意的客户加入答案，同时将对应的 customers[i] 变为 0
        // 之后的问题转化为：在 customers 中找到连续一段长度为 minutes 的子数组，使得其总和最大
        // 这部分就是应用技巧所得到的客户

        int n = customers.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                ans += customers[i];
                // customers[i] 置为 0，不影响后面
                customers[i] = 0;
            }
        }

        int cur = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {

            cur += customers[i];

            if (i >= minutes)
                cur -= customers[i - minutes];

            max = Math.max(max, cur);
        }

        return ans + max;
    }
}
