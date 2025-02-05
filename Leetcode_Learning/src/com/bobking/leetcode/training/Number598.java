package com.bobking.leetcode.training;

/**
 * @Date: 2025/2/5 23:34
 * @Author: BobKing
 * @Description:
 */
public class Number598 {

    // 参考: https://leetcode.cn/problems/range-addition-ii/solutions/3053429/nao-jin-ji-zhuan-wan-pythonjavaccgojsrus-7166/?envType=daily-question&envId=2025-02-05
    public int maxCount(int m, int n, int[][] ops) {
        int minA = m;
        int minB = n;
        for (int[] op : ops) {
            minA = Math.min(minA, op[0]);
            minB = Math.min(minB, op[1]);
        }
        return minA * minB;
    }

}
