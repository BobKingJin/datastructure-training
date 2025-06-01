package com.bobking.leetcode.training;

/**
 * @Date: 2025/6/1 11:37
 * @Author: BobKing
 * @Description:
 */
public class Number2929 {

    // 参考: https://leetcode.cn/problems/distribute-candies-among-children-ii/solutions/2522969/o1-rong-chi-yuan-li-pythonjavacgo-by-end-2woj/?envType=daily-question&envId=2025-06-01
    public long distributeCandies1(int n, int limit) {
        return c2(n + 2) - 3 * c2(n - limit + 1) + 3 * c2(n - 2 * limit) - c2(n - 3 * limit - 1);
    }

    private long c2(int n) {
        return n > 1 ? (long) n * (n - 1) / 2 : 0;
    }

    // 参考: https://leetcode.cn/problems/distribute-candies-among-children-ii/solutions/2791756/gei-xiao-peng-you-men-fen-tang-guo-ii-by-1jj9/?envType=daily-question&envId=2025-06-01
    public long distributeCandies2(int n, int limit) {
        long ans = 0;
        for (int i = 0; i <= Math.min(limit, n); i++) {
            if (n - i > 2 * limit) {
                continue;
            }
            ans += Math.min(n - i, limit) - Math.max(0, n - i - limit) + 1;
        }
        return ans;
    }


}
