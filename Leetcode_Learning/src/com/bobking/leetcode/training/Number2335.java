package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-02-12 11:52
 */
public class Number2335 {

    // 参考：https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups/solution/by-tsreaper-158c/
    public int fillCups(int[] amount) {

        Arrays.sort(amount);
        int a = amount[0];
        int b = amount[1];
        int c = amount[2];

        if(a + b <= c){
            return c;
        }else{
            int t = a + b - c;
            return t % 2 == 0 ? t / 2 + c :  t / 2 + c + 1;
        }
    }
}
