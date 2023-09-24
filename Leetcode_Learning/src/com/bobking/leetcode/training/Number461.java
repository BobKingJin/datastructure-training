package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2020-12-07 22:41
 */
public class Number461 {

    public int hammingDistance1(int x, int y) {

        int result = 0;

        for (int i = 0; i < 32; i++) {
            int m = x >>> i;
            int n = y >>> i;
            result += (((m ^ n) % 2) == 0 ? 0 : 1);
        }

        return result;
    }

    public int hammingDistance2(int x, int y) {

        int result = 0;

        int xor = x ^ y;
        // 即找到 xor 中从最右边到最左边的 1 这些位中 1 的个数
        while (xor != 0) {
            if (xor % 2 == 1)
                result++;
            xor = xor >>> 1;
        }

        return result;
    }

    public int hammingDistance3(int x, int y) {

        int result = 0;

        int xor = x ^ y;
        while (xor != 0) {
            result++;
            xor = xor & (xor - 1);
        }

        return result;
    }
}
