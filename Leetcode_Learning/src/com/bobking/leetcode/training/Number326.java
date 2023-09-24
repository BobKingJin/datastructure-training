package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-05-03 17:04
 */
public class Number326 {

    public boolean isPowerOfThree1(int n) {

        if (n <= 0)
            return false;

        while (n % 3 == 0)
            n /= 3;

        return n == 1;
    }

    Set<Integer> set = new HashSet<Integer>();

    private void init(){
        int cur = 1;
        set.add(cur);
        while (cur <= Integer.MAX_VALUE / 3) {
            cur *= 3;
            set.add(cur);
        }
    }

    public boolean isPowerOfThree2(int n) {
        init();
        return n > 0 && set.contains(n);
    }

}
