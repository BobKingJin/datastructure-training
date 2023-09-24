package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-18 12:20
 */
public class Number2169 {

    // 可以按要求模拟两数比较后相减的操作，但在两数差距十分悬殊时，会有大量连续且相同的相减操作，因此可以对模拟的过程进行优化
    // 题目中的过程即为求两个数最大公约数的「辗转相减」方法，而需要将它优化为时间复杂度更低的「辗转相除」法
    // 同时利用前文的方法统计对应相减操作的次数

    // 参考：https://leetcode.cn/problems/count-operations-to-obtain-zero/solution/de-dao-0-de-cao-zuo-shu-by-leetcode-solu-d8kh/
    public int countOperations1(int num1, int num2) {

        int ans = 0;

        while (num1 != 0 && num2 != 0) {
            ans++;
            if (num1 >= num2) {
                num1 -= num2;
            } else {
                num2 -= num1;
            }
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/count-operations-to-obtain-zero/solution/de-dao-0-de-cao-zuo-shu-by-leetcode-solu-d8kh/
    public int countOperations2(int num1, int num2) {

        int ans = 0;

        while (num1 != 0 && num2 != 0) {
            // 单纯的用暴力方法进行减，那么当 num1 > num2 时，num1 = num1 - num2 后的结果有可能仍然大于 num2
            // 利用 num1 = num1 % num2 可以保证结果一定小于 num2
            if (num1 >= num2) {
                ans += num1 / num2;
                num1 %= num2;
            } else {
                ans += num2 / num1;
                num2 %= num1;
            }
        }

        return ans;
    }
}
