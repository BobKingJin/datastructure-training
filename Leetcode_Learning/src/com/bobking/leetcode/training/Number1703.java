package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-03-05 11:19
 */
public class Number1703 {

    // 参考：https://leetcode.cn/problems/minimum-adjacent-swaps-for-k-consecutive-ones/solution/duo-tu-xin-shou-jiao-cheng-yi-bu-bu-dai-6bps4/
    public int minMoves(int[] nums, int k) {

        List<Integer> zeros = new ArrayList<Integer>();
        int count0 = 0;
        int count1 = 0;
        for (int num : nums) {
            if (num != 1)
                count0++;
            else {
                if (count1 != 0)
                    zeros.add(count0);
                count1++;
                count0 = 0;
            }
        }

        int[] preSum = new int[zeros.size() + 1];
        for (int i = 0; i < zeros.size(); i++)
            preSum[i + 1] = preSum[i] + zeros.get(i);

        int cost = 0;
        int left = 0;
        int right = k - 2;
        for (int i = left; i <= right; i++)
            cost += zeros.get(i) * (Math.min(i + 1, right - i + 1));

        int minCost = cost;
        for (int i = 1, j = i + k - 2; j < zeros.size(); i++, j++) {
            int mid = (i + j) / 2;
            cost -= preSum[mid] - preSum[i - 1];
            cost += preSum[j + 1] - preSum[mid + k % 2];
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }
}
