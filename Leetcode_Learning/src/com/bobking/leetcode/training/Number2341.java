package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-16 15:47
 */
public class Number2341 {

    public int[] numberOfPairs(int[] nums) {

        int[] cnt = new int[101];
        for (int x : nums)
            ++cnt[x];

        int s = 0;
        for (int v : cnt)
            s += v / 2;

        return new int[] {s, nums.length - s * 2};
    }
}
