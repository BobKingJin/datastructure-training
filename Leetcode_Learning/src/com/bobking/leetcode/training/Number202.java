package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Number202 {

    private int getNext(int n) {

        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }

        return totalSum;
    }

    // 参考：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
    public boolean isHappy1(int n) {

        Set<Integer> seen = new HashSet<Integer>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    // 参考：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
    public boolean isHappy2(int n) {

        int slowRunner = n;
        int fastRunner = getNext(n);

        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }

        return fastRunner == 1;
    }


}
