package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-24 7:25
 */
public class Number476 {

    // 参考: https://leetcode.cn/problems/number-complement/solutions/1052783/gong-shui-san-xie-yi-ti-shuang-jie-bian-wjl0y/
    public int findComplement1(int num) {

        // 返回对 num 的二进制表示取反的数，注意 num 的二进制表示是不包含前导零的
        // 因此主要问题求得 num 最高位 11 的位置
        //
        // 一个简单的做法是：先对 num 进行「从高到低」的检查，找到最高位 1 的位置 s，然后再对 num 进行遍历，将低位到 s 位的位置执行逐位取反操作

        if (num == 0)
            return 1;

        int s = -1;

        for (int i = 31; i >= 0; i--) {
            if (((num >> i) & 1) != 0) {
                s = i;
                break;
            }
        }

        int ans = 0;
        for (int i = 0; i < s; i++) {
            if (((num >> i) & 1) == 0)
                ans |= (1 << i);
        }
        return ans;
    }

    public int findComplement2(int num) {

        // 找比 num 大且最近的 2 的幂，然后减 1，再与 num 进行异或

        if (num == 0)
            return 1;

        // t 为比 num 大且最近的 2 的幂
        long t = 1;
        while (t <= num)
            t <<= 1;

        return (int) (num ^ (t - 1));
    }


}
