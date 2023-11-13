package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-13 7:58
 */
public class Number1497 {

    // 参考: https://leetcode.cn/problems/check-if-array-pairs-are-divisible-by-k/solutions/310418/jian-cha-shu-zu-dui-shi-fou-ke-yi-bei-k-zheng-chu-/
    public boolean canArrange(int[] arr, int k) {

        int[] mod = new int[k];
        for (int num : arr)
            ++mod[(num % k + k) % k];

        for (int i = 1; i + i < k; ++i) {
            if (mod[i] != mod[k - i])
                return false;
        }

        return mod[0] % 2 == 0;
    }
}
