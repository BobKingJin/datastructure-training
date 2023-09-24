package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-16 11:06
 */
public class Number479 {

    // 参考：https://leetcode-cn.com/problems/largest-palindrome-product/solution/by-ac_oier-t8j7/
    public int largestPalindrome(int n) {

        if (n == 1)
            return 9;

        // 对于数位为 n 的两个数而言，其乘积的位数要么是 2 * n，要么是 2 * n - 1
        // 利用回文串的特性，只需枚举回文串的前半部分即可（后半部分唯一确定），只要在枚举前半部分时按照「从大到小」进行
        // 即可确保找到的第一个合法值为最大数，对于一个数位为 n 的最大数为 10^n - 1

        // 具体的，当枚举到回文串的前半部分 i 时，利用回文串特性构造出具实际的回文数值 num，随后检查 num 能否分解成数位为 n 的数对 (a, b)
        // 利用乘法具有交换律，只需要枚举数对中的较大数即可

        // n 位表示的最大数
        int max = (int) Math.pow(10, n) - 1;
        for (int i = max; i >= 0; i--) {
            // num 即为 i 的回文数
            // 例如：i = 99 时，num = 9999  i = 98 时，num = 9889  i = 97 时，num = 9779....
            // 9999 即为 n = 2 时能表示的最大回文数， 9889 即为 n = 2 时能表示的第二大的回文数....
            long num = i;
            int t = i;
            while (t != 0) {
                num = num * 10 + (t % 10);
                t /= 10;
            }

            for (long j = max; j * j >= num; j--)
                if (num % j == 0)
                    return (int)(num % 1337);
        }

        return -1;
    }
}
