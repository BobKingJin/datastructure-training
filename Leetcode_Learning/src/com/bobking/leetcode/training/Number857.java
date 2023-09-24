package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-09-11 9:07
 */
public class Number857 {

    // 参考：https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/comments/
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {

        // 每个工人都有自己期望的价性比，雇佣 k 个工人的时候要满足每个人的实际价性比不低于他的期望，即需要按照 k 个工人中的最高期望价性比给这 k 个人开工资
        // 使用了一个大根堆，来获取 k 个工人的最大的价性比，作为 k 个工人的价性比，使用 qsum 保存 k 个工人的质量和，要给他们付的工资就是 qsum * 最大性价比

        double[][] workers = new double[quality.length][2];
        // 保存工人价性比
        for (int i = 0; i < quality.length; ++i)
            workers[i] = new double[]{(double)(wage[i]) / quality[i], (double)quality[i]};

        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE;
        // qsum 保存 k 个工人的质量和
        double qsum = 0.0;
        // 大根堆
        PriorityQueue<Double> pq = new PriorityQueue<Double>();

        for (double[] worker: workers) {
            qsum += worker[1];
            pq.add(-worker[1]);
            if (pq.size() > k)
                qsum += pq.poll();

            if (pq.size() == k)
                res = Math.min(res, qsum * worker[0]);
        }

        return res;
    }
}
