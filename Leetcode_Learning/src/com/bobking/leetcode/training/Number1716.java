package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-08 20:32
 */
public class Number1716 {

    // 参考：https://leetcode.cn/problems/calculate-money-in-leetcode-bank/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-ifit/
    public int totalMoney(int n) {

        int a = n / 7;
        int b = n % 7;

        int ans = 0;
        int k = 1;

        while (a-- > 0) {
            // 周内金额可使用「等差数列求和」公式直接求解
            ans += (k + (k + 6)) * 7 / 2;
            k++;
        }

        while (b-- > 0)
            ans += k++;

        return ans;
    }
}
