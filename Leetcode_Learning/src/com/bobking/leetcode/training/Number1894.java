package com.bobking.leetcode.training;

public class Number1894 {

    // 参考：https://leetcode.cn/problems/find-the-student-that-will-replace-the-chalk/solution/gong-shui-san-xie-yi-ti-shuang-jie-qian-kpqsk/
    public int chalkReplacer(int[] chalk, int k) {

        // 可以预处理出前缀和数组 sum，将 k 对所有学生一次循环所消耗总粉笔数（sum[n]）进行取模，得到最后一轮开始前的粉笔数量
        // 然后对前缀和数组进行二分，找到最后一位满足粉笔要求的学生，其往后一位的同学编号即是答案

        int n = chalk.length;
        long[] sum = new long[n + 1];

        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + chalk[i - 1];

        k = (int)(k % sum[n]);

        int l = 1;
        int r = n;

        while (l < r) {

            int mid = l + r + 1 >> 1;
            if (sum[mid] <= k) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return sum[r] <= k ? r : r - 1;
    }
}
