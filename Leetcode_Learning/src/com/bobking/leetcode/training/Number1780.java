package com.bobking.leetcode.training;

public class Number1780 {

    public boolean checkPowersOfThree1(int n) {
        return n < 2 || (n % 3 != 2 && checkPowersOfThree1(n / 3));
    }

    public boolean checkPowersOfThree2(int n) {

        while (n > 0) {

            if (n % 3 == 2)
                return false;

            n = n / 3;
        }

        return true;
    }
}
