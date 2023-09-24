package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-03-27 11:27
 */
public class Number2028 {

    // 参考：https://leetcode-cn.com/problems/find-missing-observations/solution/by-ac_oier-x22k/
    public int[] missingRolls(int[] rolls, int mean, int n) {

        if(rolls == null || rolls.length == 0)
            return new int[0];

        int[] res = new int[n];

        int sum = 0;
        for(Integer i : rolls)
            sum += i;

        int count = mean * (n + rolls.length) - sum;
        if(count > 6 * n || count < 1 * n)
            return new int[0];

        Arrays.fill(res, count / n);

        if (count / n * n < count) {
            int diff = count - (count / n * n);
            int index = 0;
            while (diff-- > 0)
                res[index++]++;
        }

        return res;
    }
}
