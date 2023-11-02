package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-31 7:31
 */
public class Number458 {

    // 参考: https://leetcode.cn/problems/poor-pigs/solutions/15770/hua-jie-suan-fa-458-ke-lian-de-xiao-zhu-by-guanpen/
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {

        int times = minutesToTest / minutesToDie;
        int base = times + 1;
        // base ^ ans >= buckets
        // ans >= log(buckets) / log(base)
        // 取对数
        double temp = Math.log(buckets) / Math.log(base) - 1e-5;
        return (int) Math.ceil(temp);
    }
}
