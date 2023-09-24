package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number826 {

    // 参考：https://leetcode-cn.com/problems/most-profit-assigning-work/solution/an-pai-gong-zuo-yi-da-dao-zui-da-li-yi-by-lenn123/
    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {

        if (difficulty == null || difficulty.length == 0 || profit == null || profit.length == 0
                || worker == null || worker.length == 0)
            return 0;

        int sum = 0;

        for (int w : worker) {
            int p = 0;
            for (int i = 0; i < difficulty.length; i++) {
                if (difficulty[i] > w)
                    continue;
                p = Math.max(p, profit[i]);
            }
            sum += p;
        }

        return sum;
    }

    private class Work {

        int difficulty;
        int profit;

        public Work(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }

    // 参考：https://leetcode-cn.com/problems/most-profit-assigning-work/solution/an-pai-gong-zuo-yi-da-dao-zui-da-li-yi-by-lenn123/
    public int maxProfitAssignment2(int[] difficulty, int[] profit, int[] worker) {

        if (difficulty == null || difficulty.length == 0 || profit == null || profit.length == 0
                || worker == null || worker.length == 0)
            return 0;

        int N = difficulty.length;
        Work[] help = new Work[N];
        for (int i = 0; i < N; i++)
            help[i] = new Work(difficulty[i], profit[i]);
        // 按照 difficulty 升序排列
        Arrays.sort(help, (o1, o2) -> o1.difficulty - o2.difficulty);
        // 将工人排序
        Arrays.sort(worker);

        int idx1 = 0;
        int idx2 = 0;
        int maxVal = 0;
        int res = 0;

        while (idx1 < help.length && idx2 < worker.length) {

            if (help[idx1].difficulty <= worker[idx2]) {
                maxVal = Math.max(maxVal, help[idx1].profit);
                idx1++;
            } else {
                res += maxVal;
                idx2++;
            }
        }
        // 剩余未安排工作的工人均可取得最大利润
        res += (worker.length - idx2) * maxVal;
        return res;
    }


}
