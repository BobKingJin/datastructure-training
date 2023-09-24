package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-17 10:41
 */
public class Number1103 {

    // 参考：https://leetcode.cn/problems/distribute-candies-to-people/solution/fen-tang-guo-ii-by-leetcode-solution/
    public int[] distributeCandies1(int candies, int num_people) {

        int[] ans = new int[num_people];
        int i = 0;

        while (candies != 0) {
            ans[i % num_people] += Math.min(candies, i + 1);
            candies -= Math.min(candies, i + 1);
            i += 1;
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/distribute-candies-to-people/solution/fen-tang-guo-ii-by-leetcode-solution/
    public int[] distributeCandies2(int candies, int num_people) {

        int n = num_people;

        int p = (int) (Math.sqrt(2 * candies + 0.25) - 0.5);
        int remaining = (int)(candies - (p + 1) * p * 0.5);
        int rows = p / n;
        int cols = p % n;

        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {

            d[i] = (i + 1) * rows + (int)(rows * (rows - 1) * 0.5) * n;

            if (i < cols)
                d[i] += i + 1 + rows * n;
        }

        d[cols] += remaining;
        return d;
    }
}
