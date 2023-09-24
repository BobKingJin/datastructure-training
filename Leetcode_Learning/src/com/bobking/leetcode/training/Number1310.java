package com.bobking.leetcode.training;

public class Number1310 {

    // 参考：程序猿代码指南P324
    // 参考：https://leetcode-cn.com/problems/xor-queries-of-a-subarray/solution/gong-shui-san-xie-yi-ti-shuang-jie-shu-z-rcgu/
    public int[] xorQueries(int[] arr, int[][] queries) {

        if (arr == null || arr.length == 0 || queries == null || queries.length == 0)
            return null;

        int[] res = new int[queries.length];
        // sum[0] 默认为 0，而 0 ^ num = num
        int[] eorSum = new int[arr.length + 1];

        for (int i = 1; i <= arr.length; i++)
            eorSum[i] = eorSum[i - 1] ^ arr[i - 1];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0] + 1;
            int r = queries[i][1] + 1;
            // 0 ~ l 的异或和为 eor1，0 ~ r 的异或和为 eor2
            // 即 eorSum：0 ... eor1 ... eor2
            // index： 0 .....i ......j
            // 那么 i ~ j 的异或和为 eor(j) ^ eor(i - 1)
            // 假设 i ~ j 的异或和为 eor, 因为 eor(j) 等于 eor(i - 1) ^ eor = eor(j)
            // 那么 eor = eor(j) ^ eor(i - 1)
            res[i] = eorSum[r] ^ eorSum[l - 1];
        }

        return res;
    }
}
