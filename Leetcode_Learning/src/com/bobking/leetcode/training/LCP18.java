package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-11-10 11:12
 */
public class LCP18 {

    public int breakfastNumber(int[] staple, int[] drinks, int x) {

        Arrays.sort(staple);
        Arrays.sort(drinks);
        int ans = 0;
        int i = 0;
        int j = drinks.length - 1;

        while (i < staple.length && j >= 0) {
            if (staple[i] + drinks[j] > x) {
                j--;
            } else {
                ans += (j + 1);
                ans %= 1000000007;
                i++;
            }
        }
        return ans;
    }
}
