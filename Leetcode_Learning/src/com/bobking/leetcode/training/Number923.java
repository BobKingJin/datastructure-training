package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-10-29 9:19
 */
public class Number923 {

    // 参考：https://leetcode.cn/problems/3sum-with-multiplicity/solution/san-shu-zhi-he-de-duo-chong-ke-neng-by-leetcode/
    public int threeSumMulti(int[] arr, int target) {

        int MOD = 1000000007;
        long ans = 0;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; ++i) {

            // 当 A[j] + A[k] == T，寻找 T = target - A[i]
            int T = target - arr[i];
            int j = i + 1;
            int k = arr.length - 1;

            while (j < k) {
                if (arr[j] + arr[k] < T) {
                    j++;
                } else if (arr[j] + arr[k] > T) {
                    k--;
                } else if (arr[j] != arr[k]) {
                    int left = 1;
                    int right = 1;
                    while (j + 1 < k && arr[j] == arr[j + 1]) {
                        left++;
                        j++;
                    }
                    while (k - 1 > j && arr[k] == arr[k - 1]) {
                        right++;
                        k--;
                    }
                    ans += left * right;
                    ans %= MOD;
                    j++;
                    k--;
                } else {
                    ans += (k - j + 1) * (k - j) / 2;
                    ans %= MOD;
                    break;
                }
            }
        }

        return (int) ans;
    }
}
