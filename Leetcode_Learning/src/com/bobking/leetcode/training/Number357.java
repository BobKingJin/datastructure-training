package com.bobking.leetcode.training;

public class Number357 {

    // 参考：https://leetcode-cn.com/problems/count-numbers-with-unique-digits/solution/by-ac_oier-6tfl/
    public int countNumbersWithUniqueDigits(int n) {
        
        if (n == 0) 
            return 1;

        // 由于不能含有前导 0，最高位可选择的数值个数为 9，而从次高位开始到最低位，可选的个数从 9 开始逐一递减
        // 利用乘法原理，每位数可选的数值个数相乘即是长度为 n 的数的可能方案数 cur，而所有长度 [1, n] 的方案数累加即是答案
        int res = 10;
        for (int i = 2, last = 9; i <= n; i++) {
            int cur = last * (10 - i + 1);
            res += cur;
            last = cur;
        }

        return res;
    }
}
