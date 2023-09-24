package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-07 7:25
 */
public class Number1017 {

    // 参考：https://leetcode.cn/problems/convert-to-base-2/solution/python3javacgotypescript-yi-ti-yi-jie-mo-5edi/
    public String baseNeg2(int n) {

        // 可以判断 n 从低位到高位的每一位，如果该位为 1，那么答案的该位为 1
        // 否则为 0。如果该位为 1，那么我需要将 n 减去 k。接下来更新 n = ⌊n / 2⌋,
        // k = −k。继续判断下一位
        // 对于 2^i，如果 i 为偶数时，此时 2^i = (-2)^i
        // 对于 2^i，如果 i 为奇数时，此时 2^i = (-2)^(i + 1) + (-2)^i

        if (n == 0)
            return "0";

        // 偶正奇负
        int k = 1;
        StringBuilder ans = new StringBuilder();

        while (n != 0) {
            if (n % 2 != 0) {
                ans.append(1);
                // 在奇数 i 位的时候给 n 补齐剩余价值，即等效于 n + 2^i
                n -= k;
            } else {
                ans.append(0);
            }
            k *= -1;
            n /= 2;
        }
        return ans.reverse().toString();
    }
}
