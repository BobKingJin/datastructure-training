package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-26 12:22
 */
public class Number1281 {

    public int subtractProductAndSum(int n) {

        if(n < 1)
            return 0;

        int sum = 0;
        int product = 1;

        while (n > 0) {
            int num = n % 10;
            sum += num;
            product *= num;
            n /= 10;
        }

        return product - sum;
    }

}
