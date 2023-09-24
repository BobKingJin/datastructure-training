package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-03-30 22:31
 */
public class Number1637 {

    // 参考：https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points/solution/python3javacgo-yi-ti-shuang-jie-pai-xu-t-pc0a/
    public int maxWidthOfVerticalArea1(int[][] points) {

        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 0; i < points.length - 1; ++i)
            ans = Math.max(ans, points[i + 1][0] - points[i][0]);

        return ans;
    }

    // 参考：https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points/solution/python3javacgo-yi-ti-shuang-jie-pai-xu-t-pc0a/
    public int maxWidthOfVerticalArea2(int[][] points) {

        int n = points.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i)
            nums[i] = points[i][0];

        final int inf = 1 << 30;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }
        int bucketSize = Math.max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][2];
        for (int[] bucket : buckets) {
            bucket[0] = inf;
            bucket[1] = -inf;
        }
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i][0] = Math.min(buckets[i][0], v);
            buckets[i][1] = Math.max(buckets[i][1], v);
        }
        int prev = inf;
        int ans = 0;
        for (int[] bucket : buckets) {
            if (bucket[0] > bucket[1])
                continue;
            ans = Math.max(ans, bucket[0] - prev);
            prev = bucket[1];
        }
        return ans;
    }
}
