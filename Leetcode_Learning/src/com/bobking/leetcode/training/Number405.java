package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-29 11:38
 */
public class Number405 {

    // 参考: https://leetcode.cn/problems/convert-a-number-to-hexadecimal/solutions/1027527/gong-shui-san-xie-yi-ti-shuang-jie-jin-z-d93o/
    public String toHex1(int num) {

        if (num == 0)
            return "0";

        long cur = num;
        StringBuilder sb = new StringBuilder();

        // 对于一个给定的负数, 只需要将其加上 0xFFFFFFFF + 1, 再减去该负数的绝对值即可得到该负数对应的补码值
        if(num < 0)
            // 0xFFFFFFFF + 1化成 十进制 为 Math.pow(2, 32)
            cur = (long)(Math.pow(2, 32) + cur);

        while (num != 0) {
            long u = cur % 16;
            char c = (char)(u + '0');
            if (u >= 10)
                c = (char)(u - 10 + 'a');
            sb.append(c);
            cur /= 16;
        }
        return sb.reverse().toString();
    }

    // 参考: https://leetcode.cn/problems/convert-a-number-to-hexadecimal/solutions/1027527/gong-shui-san-xie-yi-ti-shuang-jie-jin-z-d93o/
    public String toHex2(int num) {

        if (num == 0)
            return "0";

        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            int u = num & 15;
            char c = (char)(u + '0');
            if (u >= 10)
                c = (char)(u - 10 + 'a');
            sb.append(c);
            num >>>= 4;
        }
        return sb.reverse().toString();
    }
}
