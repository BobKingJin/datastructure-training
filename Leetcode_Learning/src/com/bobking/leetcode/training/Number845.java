package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-03 11:41
 */
public class Number845 {

    // 参考：https://leetcode.cn/problems/longest-mountain-in-array/solution/shu-zu-zhong-de-zui-chang-shan-mai-by-leetcode-sol/
    public int longestMountain1(int[] arr) {

        int n = arr.length;
        if (n == 0)
            return 0;

        int[] left = new int[n];
        for (int i = 1; i < n; ++i)
            left[i] = arr[i - 1] < arr[i] ? left[i - 1] + 1 : 0;

        int[] right = new int[n];
        for (int i = n - 2; i >= 0; --i)
            right[i] = arr[i + 1] < arr[i] ? right[i + 1] + 1 : 0;

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0)
                ans = Math.max(ans, left[i] + right[i] + 1);
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/longest-mountain-in-array/solution/shu-zu-zhong-de-zui-chang-shan-mai-by-leetcode-sol/
    public int longestMountain2(int[] arr) {

        int n = arr.length;
        int ans = 0;
        int left = 0;

        while (left + 2 < n) {
            int right = left + 1;
            if (arr[left] < arr[left + 1]) {
                while (right + 1 < n && arr[right] < arr[right + 1])
                    ++right;
                if (right < n - 1 && arr[right] > arr[right + 1]) {
                    while (right + 1 < n && arr[right] > arr[right + 1])
                        ++right;
                    ans = Math.max(ans, right - left + 1);
                } else {
                    ++right;
                }
            }
            left = right;
        }

        return ans;
    }
}
