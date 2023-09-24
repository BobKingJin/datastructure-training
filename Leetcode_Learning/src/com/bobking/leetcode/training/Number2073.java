package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-20 22:42
 */
public class Number2073 {

    // 参考：https://leetcode.cn/problems/time-needed-to-buy-tickets/solution/mai-piao-xu-yao-de-shi-jian-by-leetcode-jnfxx/
    public int timeRequiredToBuy(int[] tickets, int k) {

        int ans = 0;

        for (int i = 0; i < tickets.length; i++)
            ans += Math.min(tickets[i], i <= k ? tickets[k] : tickets[k] - 1);

        return ans;
    }
}
