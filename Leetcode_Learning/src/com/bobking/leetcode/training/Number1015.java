package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-10 6:36
 */
public class Number1015 {

    // 参考：https://leetcode.cn/problems/smallest-integer-divisible-by-k/solution/javajie-fa-yi-ji-zheng-ming-de-si-lu-by-jiangzk/
    // 参考：https://leetcode.cn/problems/smallest-integer-divisible-by-k/solution/san-chong-suan-fa-you-hua-pythonjavacgo-tk4cj/
    public int smallestRepunitDivByK(int k) {

        if (k % 2 == 0 || k % 5 == 0)
            return -1;

        int temp = 1;
        int len = 1;

        while (temp % k != 0) {
            temp = temp % k;
            temp = temp * 10 + 1;
            len += 1;
        }

        return len;
    }
}
