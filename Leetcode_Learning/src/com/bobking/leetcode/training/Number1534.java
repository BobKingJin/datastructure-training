package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-06 7:13
 */
public class Number1534 {

    // 参考：https://leetcode.cn/problems/count-good-triplets/solution/tong-ji-hao-san-yuan-zu-by-leetcode-solution/
    public int countGoodTriplets1(int[] arr, int a, int b, int c) {

        int n = arr.length;
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k)
                    if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                        ++cnt;
                }
            }
        }
        return cnt;
    }

    // 参考：https://leetcode.cn/problems/count-good-triplets/solution/tong-ji-hao-san-yuan-zu-by-leetcode-solution/
    public int countGoodTriplets2(int[] arr, int a, int b, int c) {

        int ans = 0;
        int n = arr.length;
        int[] sum = new int[1001];

        for (int j = 0; j < n; ++j) {
            for (int k = j + 1 ; k < n; ++k) {
                if (Math.abs(arr[j] - arr[k]) <= b) {
                    int lj = arr[j] - a;
                    int rj = arr[j] + a;
                    int lk = arr[k] - c;
                    int rk = arr[k] + c;
                    int l = Math.max(0, Math.max(lj, lk));
                    int r = Math.min(1000, Math.min(rj, rk));
                    if (l <= r) {
                        if (l == 0) {
                            ans += sum[r];
                        } else {
                            ans += sum[r] - sum[l - 1];
                        }
                    }
                }
            }

            for (int k = arr[j]; k <= 1000; ++k)
                ++sum[k];
        }
        return ans;
    }
}
