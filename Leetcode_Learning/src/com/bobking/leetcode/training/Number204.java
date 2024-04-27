package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number204 {

    // 参考：https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/
    public int countPrimes(int n) {

        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);

        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i])
                count++;
        }
        return count;
    }
}
