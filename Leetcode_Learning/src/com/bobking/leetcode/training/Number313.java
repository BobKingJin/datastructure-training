package com.bobking.leetcode.training;

import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-06-05 12:55
 */
public class Number313 {

    // 参考：https://leetcode.cn/problems/super-ugly-number/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-jyow/
    public int nthSuperUglyNumber1(int n, int[] primes) {

        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        q.add(1);

        while (n-- > 0) {

            int x = q.poll();

            if (n == 0)
                return x;

            for (int k : primes) {
                if (k <= Integer.MAX_VALUE / x)
                    q.add(k * x);
                if (x % k == 0)
                    break;
            }
        }

        // never
        return -1;
    }
    // 参考：https://leetcode.cn/problems/super-ugly-number/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-jyow/
    public int nthSuperUglyNumber2(int n, int[] primes) {

        int m = primes.length;
        // 小到大
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);

        for (int i = 0; i < m; i++)
            q.add(new int[]{primes[i], i, 0});

        int[] ans = new int[n];
        ans[0] = 1;
        for (int j = 1; j < n; ) {
            int[] poll = q.poll();
            int val = poll[0];
            int i = poll[1];
            int idx = poll[2];

            if (val != ans[j - 1])
                ans[j++] = val;

            q.add(new int[]{ans[idx + 1] * primes[i], i, idx + 1});
        }

        return ans[n - 1];
    }

}
