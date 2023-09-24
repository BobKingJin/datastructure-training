package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-07 8:25
 */
public class Number1010 {

    // 参考：https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60/solution/java-2ms-ji-bai-10000-by-keen0126/
    public int numPairsDivisibleBy60(int[] time) {

        int count = 0;
        // 整数对 60 取模，可能有 60 种余数。故初始化一个长度为 60 的数组，统计各余数出现的次数
        int[] seconds = new int[60];

        for(int t : time)
            seconds[t % 60] += 1;

        // 余数为 0 的情况，只能同余数为 0 的情况组合
        // 0 的情况出现 k 次，则只能在 k 中任选两次进行两两组合
        count += combination(seconds[0], 2);
        // 同理
        count += combination(seconds[30], 2);
        int i = 1;
        int j = 59;

        while(i < j)
            count += seconds[i++] * seconds[j--];

        return count;
    }

    private int combination(int n, int k) {

        long result = 1;

        for(int i = 1; i <= k; i++)
            result = result * (n - i + 1) / i;

        return (int)result;
    }
}
