package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-03 10:45
 */
public class Number829 {

    // 参考：https://leetcode.cn/problems/consecutive-numbers-sum/solution/by-ac_oier-220q/
    public int consecutiveNumbersSum(int n) {
        
        int res = 0; 
        n *= 2;
        for (int k = 1; k * k < n; k++) {

            if (n % k != 0)
                continue;
            if ((n / k - (k - 1)) % 2 == 0)
                res++;
        }

        return res;
    }
}
