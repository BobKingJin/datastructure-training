package com.bobking.leetcode.training;

import java.util.PriorityQueue;

public class Number786 {

    // 优先队列
    // 参考：https://leetcode.cn/problems/k-th-smallest-prime-fraction/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-8ymk/
    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {

        int n = arr.length;
        // 大根堆
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> Double.compare(b[0] * 1.0 / b[1], a[0] * 1.0 / a[1]));

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                double t = arr[i] * 1.0 / arr[j];
                if (q.size() < k || q.peek()[0] * 1.0 / q.peek()[1] > t) {

                    if (q.size() == k)
                        q.poll();

                    q.add(new int[]{arr[i], arr[j]});
                }
            }
        }

        return q.poll();
    }

    // 多路归并
    // 参考：https://leetcode.cn/problems/k-th-smallest-prime-fraction/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-8ymk/
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {

        int n = arr.length;
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((a, b) -> {
            double i1 = arr[a[0]] * 1.0 / arr[a[1]];
            double i2 = arr[b[0]] * 1.0 / arr[b[1]];
            return Double.compare(i1, i2);
        });

        for (int i = 1; i < n; i++)
            q.add(new int[]{0, i});

        while (k-- > 1) {
            int[] poll = q.poll();
            int i = poll[0];
            int j = poll[1];
            if (i + 1 < j)
                q.add(new int[]{i + 1, j});
        }

        int[] poll = q.poll();
        return new int[]{arr[poll[0]], arr[poll[1]]};
    }

    double eps = 1e-8;
    int[] arr;
    int n;
    int a;
    int b;

    // 二分 + 双指针
    // 参考：https://leetcode.cn/problems/k-th-smallest-prime-fraction/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-8ymk/
    public int[] kthSmallestPrimeFraction3(int[] arr, int k) {

        this.arr = arr;
        n = arr.length;
        double l = 0, r = 1;
        while (r - l > eps) {
            double mid = (l + r) / 2;
            if (check(mid) >= k) {
                r = mid;
            } else {
                l = mid;
            }

        }
        return new int[]{a, b};
    }

    private int check(double x){

        int ans = 0;

        for (int i = 0, j = 1; j < n; j++) {

            while (arr[i + 1] * 1.0 / arr[j] <= x)
                i++;

            if (arr[i] * 1.0 / arr[j] <= x)
                ans += i + 1;

            if (Math.abs(arr[i] * 1.0 / arr[j] - x) < eps) {
                a = arr[i];
                b = arr[j];
            }
        }

        return ans;
    }

}
