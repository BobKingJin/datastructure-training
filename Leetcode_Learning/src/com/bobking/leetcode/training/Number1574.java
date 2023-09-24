package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-25 0:22
 */
public class Number1574 {

    // 参考：https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/solution/java-jian-dan-on-by-avlgood2018/
    public int findLengthOfShortestSubarray(int[] arr) {

        int n = arr.length;
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left+1])
            left++;

        // [0...left] 有序
        if (left == n - 1)
            return 0;

        // [right...n - 1] 有序
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right])
            right--;

        // 完全删除一边 [left + 1, n - 1], 或者 [0...right - 1]
        int result = Math.min(n - left - 1, right);

        // 左边和右边各保留一部分
        int i = 0;
        int j = right;

        while (i <= left && j <= n - 1) {
            if (arr[i] <= arr[j]) {
                // [0...i] 和 [j...n-1] 有序, 删除 [i+1...j-1]
                result = Math.min(result, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
