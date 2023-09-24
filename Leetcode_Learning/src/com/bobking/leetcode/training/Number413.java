package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-13 18:33
 */
public class Number413 {

    public int numberOfArithmeticSlices1(int[] nums) {

        if (nums == null || nums.length < 3)
            return 0;

        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {

            int dif = nums[i + 1] - nums[i];
            for (int j = i + 2; j < nums.length; j++) {
                // 判断 i - j 之间是否为等差数列
                int k = 0;
                for (k = i + 1; k <= j; k++) {
                    if (nums[k] - nums[k - 1] != dif)
                        break;
                }
                if (k > j)
                    res++;
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/arithmetic-slices/solution/gong-shui-san-xie-shuang-zhi-zhen-qiu-ji-ef1q/
    public int numberOfArithmeticSlices6(int[] nums) {

        if (nums == null || nums.length < 3)
            return 0;

        int res = 0;

        for (int i = 0; i < nums.length - 2; ) {
            int j = i;
            int d = nums[i + 1] - nums[i];
            while (j + 1 < nums.length && nums[j + 1] - nums[j] == d)
                j++;
            int len = j - i + 1;
            // a1：长度为 len 的子数组数量；an：长度为 3 的子数组数量
            int a1 = 1;
            int an = len - 3 + 1;
            // 符合条件（长度大于等于3）的子数组的数量为「差值数列求和」结果
            // 长度为 3 的子数组数量 + 长度为 4 的子数组数量 + ... + 长度为 len 的子数组数量
            // （首项 + 尾项） * 项数 / 2
            int cnt = (a1 + an) * an / 2;
            res += cnt;
            i = j;
        }

        return res;
    }

    public int numberOfArithmeticSlices2(int[] nums) {

        if (nums == null || nums.length < 3)
            return 0;

        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {

            int diff = nums[i + 1] - nums[i];
            for (int j = i + 2; j < nums.length; j++) {
                // 判断 i - j 之间是否为等差数列
                if (nums[j] - nums[j - 1] == diff){
                    res++;
                } else{
                    break;
                }
            }
        }

        return res;
    }

    int res = 0;

    public int numberOfArithmeticSlices3(int[] nums) {

        if (nums == null || nums.length < 1)
            return 0;

        recursion(nums, nums.length - 1);
        return res;
    }

    public int recursion(int[] nums, int index) {

        if (index < 2)
            return 0;

        int count = 0;
        if (nums[index] - nums[index - 1] == nums[index - 1] - nums[index - 2]) {
            count = 1 + recursion(nums, index - 1);
            res += count;
        } else {
            recursion(nums, index - 1);
        }

        return count;
    }

    public int numberOfArithmeticSlices4(int[] nums) {

        if (nums == null || nums.length < 1)
            return 0;

        int res = 0;
        // dp[i] 表示 0 - i 之前符合等差数列的个数
        int[] dp = new int[nums.length];

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }

        return res;
    }

    public int numberOfArithmeticSlices5(int[] nums) {

        if (nums == null || nums.length < 1)
            return 0;

        int res = 0;
        // dp[i]表示 0 - i 之前符合等差数列的个数
        // int[] dp = new int[nums.length];
        int dp = 0;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp += 1;
                res += dp;
            } else {
                dp = 0;
            }
        }

        return res;
    }
}
