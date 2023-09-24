package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-08 21:13
 */
public class Number1033 {

    // 参考：https://leetcode.cn/problems/moving-stones-until-consecutive/solution/javade-jian-dan-shi-xian-hao-li-jie-by-wo-lai-xue-/
    public int[] numMovesStones(int a, int b, int c) {

        // a 指向最小值
        // c 指向最大值

        int t;
        if (a > b) {
            t = a;
            a = b;
            b = t;
        }
        if (a > c) {
            t = a;
            a = c;
            c = t;
        }
        if (b > c) {
            t = b;
            b = c;
            c = t;
        }
        // a，b，c相邻无法移动
        if (a == b - 1 && a == c - 2)
            return new int[]{0, 0};

        // 有两个数相邻或相近（隔一位）
        if (b == a + 1 || c == b + 1 || b == a + 2 || b == c - 2)
            return new int[]{1, c - a - 2};

        return new int[]{2, c - a - 2};
    }
}
