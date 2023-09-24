package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-02 11:22
 */
public class Interview16_05 {

    /**
     * n! = 1 * 2 * 3 * 4 * (1 * 5) * ... * (2 * 5) * ... * (3 * 5) ...
     * 发现每隔 5 个数就会出现 一个 5，因此只需要通过 n / 5 来计算存在存在多少个 5 个数，那么就对应的存在多少个 5
     * 但是，也发现每隔 25 个数会出现 一个 25， 而 25 存在 两个 5，上面只计算了 25 的一个 5，因此需要 n / 25 来计算存在多少个 25，加上它遗漏的 5
     * 同时还发现每隔 125 个数会出现一个 125，而 125 存在 三个 5，上面只计算了 125 的两个 5，因此需要 n / 125 来计算存在多少个 125，加上它遗漏的 5
     * ...
     * 因此 count = n / 5 + n / 25 + n / 125 + ...
     * 最终分母可能过大溢出，上面的式子可以进行转换 count = n / 5 + n / 5 / 5 + n / 5 / 5 / 5 + ...
     * 因此，这样进行循环 n /= 5; count += n; 这样，第一次加上的就是 每隔 5 个数的 5 的个数，第二次加上的就是 每隔 25 个数的 5 的个数 ...
     */

    // 参考：https://leetcode.cn/problems/factorial-zeros-lcci/solution/zhai-zi-ping-lun-qu-suan-tou-wang-bazuo-zhe-de-dai/
    public int trailingZeroes(int n) {

        int count = 0;

        while(n >= 5){
            n /= 5;
            count += n;
        }

        return count;
    }
}
