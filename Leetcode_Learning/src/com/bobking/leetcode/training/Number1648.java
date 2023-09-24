package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-08 10:31
 */
public class Number1648 {

    // 参考：https://leetcode.cn/problems/sell-diminishing-valued-colored-balls/solution/xiao-shou-jie-zhi-jian-shao-de-yan-se-qiu-by-zerot/
    public int maxProfit(int[] inventory, int orders) {

        int mod = (int) (1e9 + 7);
        int l = 0;
        int r = maxNum(inventory);

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (provideOrders(inventory, mid) <= orders) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        long res = 0;
        for (int num : inventory) {
            if (num >= l) {
                orders -= (num - l + 1);
                long first = l;
                long last = num;
                long n = num - l + 1;
                res = (res + ((first + last) * n / 2) % mod) % mod;
            }
        }
        res = (res + (long) orders * (l - 1) % mod) % mod;
        return (int) res;
    }

    private long provideOrders(int[] inventory, int m) {
        long orders = 0;
        for (int num : inventory)
            orders += Math.max(num - m + 1, 0);
        return orders;
    }

    private int maxNum(int[] inventory) {
        int max = 0;
        for (int num : inventory)
            max = Math.max(max, num);
        return max;
    }
}
