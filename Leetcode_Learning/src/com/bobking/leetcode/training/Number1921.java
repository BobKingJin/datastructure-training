package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-18 8:11
 */
public class Number1921 {

    public int eliminateMaximum(int[] dist, int[] speed) {

        int n = dist.length;
        // 对每只怪物的最迟消灭时间进行计数
        // T = (dist[i] - 1) / speed[i]
        int[] count = new int[n];

        for(int i = 0; i < n; i++) {
            // 怪物需要在 time 时间内被消灭
            int time = (dist[i] - 1) / speed[i];
            // time >= n的怪物不用管
            if(time < n)
                count[time]++;
        }
        // 能够击杀怪物的数量
        int kill = 0;
        for(int i = 0; i < n; i++) {
            // 每过一秒能多击杀一只怪物
            kill++;
            // 减去限定时间需要击杀的怪物
            kill -= count[i];
            // 如果怪物到达城市
            if(kill < 0)
                return i + 1;
        }

        return n;
    }
}
