package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-25 20:23
 */
public class Number338 {

    public int[] countBits1(int num) {

        if (num < 0) {
            return null;
        }

        int[] res = new int[num + 1];

        for (int i = 0; i <= num; i++) {
            int count = 0;
            int maxBit = 31;
            while (maxBit >= 0) {
                if (((i >>> maxBit) & 1) == 1) {
                    count++;
                }
                maxBit--;
            }
            res[i] = count;
        }

        return res;
    }

    public int[] countBits2(int num) {

        if (num < 0) {
            return null;
        }

        int[] res = new int[num + 1];

        for (int i = 0; i <= num; i++) {
            int count = 0;
            while (i > 0) {
                // 即循环让 i 中最右边的 1 变为 0
                i = i & (i - 1);
                count++;
            }
            res[i] = count;
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/counting-bits/solution/bi-te-wei-ji-shu-by-leetcode-solution-0t1i/
    public int[] countBits3(int num) {

        if (num < 0) {
            return null;
        }

        int[] res = new int[num + 1];
        int maxBit = 0;

        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                maxBit = i;
            }
            res[i] = res[i - maxBit] + 1;
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/counting-bits/solution/hen-qing-xi-de-si-lu-by-duadua/
    public int[] countBits4(int num) {

        if (num < 0) {
            return null;
        }

        int[] res = new int[num + 1];

        // 奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1
        // 偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多
        // 因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >>> 1] + (i & 1);
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/counting-bits/solution/hen-qing-xi-de-si-lu-by-duadua/
    public int[] countBits5(int num) {

        if (num < 0) {
            return null;
        }

        int[] res = new int[num + 1];

        // x & (x - 1)      x - 1是将 x 中最左的1，由1变为0
        // 那么 x & (x - 1) 就是将 x 的最低设置位从 1 变成 0 之后的数
        for (int i = 1; i <= num; i++) {
            res[i] = res[i & (i - 1)] + 1;
        }

        return res;
    }
}
