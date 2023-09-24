package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-31 12:02
 */
public class Number528 {

    // 参考：https://leetcode.cn/problems/random-pick-with-weight/solution/gong-shui-san-xie-yi-ti-shuang-jie-qian-8bx50/
    private class Solution {

        int[] sum;

        public Solution(int[] w) {

            int n = w.length;
            sum = new int[n + 1];
            for (int i = 1; i <= n; i++)
                sum[i] = sum[i - 1] + w[i - 1];
        }

        public int pickIndex() {

            // 使用随机函数参数产生 [1, sum[n - 1]] 范围内的随机数，通过「二分」前缀和数组即可找到分布位置对应的原始下标值

            int n = sum.length;
            int t = (int) (Math.random() * sum[n - 1]) + 1;
            int l = 1;
            int r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (sum[mid] >= t) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }

            return r - 1;
        }
    }
}
