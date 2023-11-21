package com.bobking.leetcode.training;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2023-01-19 11:09
 */
public class Number2386 {

    // 参考：https://leetcode.cn/problems/find-the-k-sum-of-an-array/solution/zhuan-huan-dui-by-endlesscheng-8yiq/
    public long kSum1(int[] nums, int k) {

        // 所有非负数的和
        long sum = 0L;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
        }

        Arrays.sort(nums);
        // 大根堆
        // 维护子序列的和，以及（后续需要减去的）数字的下标 i
        PriorityQueue<AbstractMap.SimpleEntry<Long, Integer>> pq = new PriorityQueue<AbstractMap.SimpleEntry<Long, Integer>>((a, b) -> Long.compare(b.getKey(), a.getKey()));
        pq.offer(new AbstractMap.SimpleEntry<Long, Integer>(sum, 0));

        while (--k > 0) {
            AbstractMap.SimpleEntry<Long, Integer> p = pq.poll();
            Long s = p.getKey();
            Integer i = p.getValue();
            // 每次弹出堆顶时，将子序列的和减去 nums[i]，并考虑是否保留 nums[i − 1]，从而满足子序列每个元素「选或不选」的要求
            if (i < nums.length) {
                // 保留 nums[i - 1]
                pq.offer(new AbstractMap.SimpleEntry<Long, Integer>(s - nums[i], i + 1));
                if (i > 0)
                    // 不保留 nums[i - 1]，把之前减去的加回来
                    pq.offer(new AbstractMap.SimpleEntry<Long, Integer>(s - nums[i] + nums[i - 1], i + 1));
            }
        }
        return pq.peek().getKey();
    }

    int[] nums;
    long limit;
    int k;
    int cnt;

    private void dfs(int i, long s) {
        if (i == nums.length || cnt >= k || s + nums[i] > limit)
            return;
        ++cnt;
        dfs(i + 1, s + nums[i]);
        dfs(i + 1, s);
    }

    // 参考：https://leetcode.cn/problems/find-the-k-sum-of-an-array/solution/zhuan-huan-dui-by-endlesscheng-8yiq/
    public long kSum2(int[] nums, int k) {

        long sum = 0L;
        long tot = 0L;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                sum += nums[i];
            } else {
                nums[i] = -nums[i];
            }
            tot += nums[i];
        }

        Arrays.sort(nums);

        this.nums = nums;
        this.k = k - 1;
        long left = 0L;
        long right = tot;

        while (left < right) {
            long mid = (left + right) / 2;
            this.limit = mid;
            // 统计元素和 s 不超过 limit 的子序列个数 cnt
            cnt = 0;
            dfs(0, 0L);
            if (cnt >= k - 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return sum - left;
    }
}
