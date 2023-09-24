package com.bobking.leetcode.training;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Number502 {

    private class Program {

        public int cost;
        public int profit;

        public Program(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    // 定义小根堆
    private class CostMinComp implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    // 定义大根堆
    private class ProfitMaxComp implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    // 参考：程序猿代码指南P418
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {

        if (k < 1 || w < 0 || profits == null || profits.length < 1 || capital == null || capital.length < 1)
            return 0;

        PriorityQueue<Program> costMinHeap = new PriorityQueue<Program>(new CostMinComp());
        PriorityQueue<Program> profitMaxHeap = new PriorityQueue<Program>(new ProfitMaxComp());

        for (int i = 0; i < capital.length; i++)
            costMinHeap.add(new Program(capital[i], profits[i]));

        for (int i = 0; i < k; i++) {

            while (!costMinHeap.isEmpty() && costMinHeap.peek().cost <= w)
                profitMaxHeap.add(costMinHeap.poll());

            if (profitMaxHeap.isEmpty())
                return w;

            w += profitMaxHeap.poll().profit;
        }

        return w;
    }
}
