package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-09 14:29
 */
public class Number1806 {

    // 参考：https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/solution/shu-xue-on-suan-fa-by-arsenal-591-xatz/
    public int reinitializePermutation1(int n) {

        if (n == 2)
            return 1;

        int k = 1;
        int pow2 = 2;

        while (pow2 != 1) {
            k++;
            pow2 = pow2 * 2 % (n - 1);
        }

        return k;
    }

    // 参考：https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/solution/liang-chong-jie-fa-mo-ni-mo-ni-you-hua-b-2ijm/
    public int reinitializePermutation2(int n) {

        int[] prem = new int[n];
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            prem[i] = i;

        int i;
        int step = 1;
        while (true) {
            for (i = 0; i < n; i++)
                arr[i] = i % 2 == 0 ? prem[i / 2] : prem[(n - 1 + i) / 2];
            for (i = 0; i < n && arr[i] == i; i++) ;
            if (i == n)
                return step;
            for (i = 0; i < n; i++)
                prem[i] = arr[i];
            step++;
        }
    }

    // 参考：https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/solution/liang-chong-jie-fa-mo-ni-mo-ni-you-hua-b-2ijm/
    public int reinitializePermutation3(int n) {

        int i = 1;
        int step = 1;
        while (true) {
            i = i % 2 == 0 ? i / 2 : (n - 1 + i) / 2;
            if (i == 1)
                return step;
            step++;
        }
    }



}
