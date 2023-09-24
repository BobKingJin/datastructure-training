package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-05 11:03
 */
public class Number1599 {

    // 参考：https://leetcode.cn/problems/maximum-profit-of-operating-a-centennial-wheel/solution/python3javacgo-yi-ti-yi-jie-mo-ni-by-lcb-6kz7/
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {

        int ans = -1;
        int mx = 0;
        int t = 0;
        int wait = 0;
        int i = 0;

        while (wait > 0 || i < customers.length) {
            wait += i < customers.length ? customers[i] : 0;
            int up = Math.min(4, wait);
            wait -= up;
            ++i;
            t += up * boardingCost - runningCost;
            if (t > mx) {
                mx = t;
                ans = i;
            }
        }
        return ans;
    }
}
