package com.bobking.leetcode.training;

public class Number1374 {

    public String generateTheString(int n) {

        char[] res = new char[n];

        if (n % 2 == 0)
            res[--n] = 'e';

        for (int i = n - 1; i >= 0; i--)
            res[i] = 'o';

        return String.valueOf(res);
    }
}
