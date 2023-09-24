package com.bobking.leetcode.training;

public class Number396 {
    
    // 参考：https://leetcode-cn.com/problems/rotate-function/solution/by-ac_oier-sxbi/
    public int maxRotateFunction(int[] nums) {

        // 假设当前处理到下标为 [i, i + n - 1](窗口长度为 n，0 <= i <= (n - 1) ) 的滑动窗口，根据题意，当前结果为：
        // cur = nums[i] * 0 + nums[i + 1] * 1 + ... + nums[i + n - 1] * (n - 1)
        // 当窗口往后移动一位，也就是窗口的右端点来到 i + n 的位置，左端点来到 i + 1 的位置
        // cur = nums[i + 1] * 0 + nums[i + 2] * 1 + ... + nums[i + n] * (n - 1)
        // 需要增加「新右端点」的值，即增加 nums[i + n] * (n - 1)，同时减去「旧左端点」的值，即减少 nums[i] * 0（固定为 0）
        // 然后更新新旧窗口的公共部分 [i + 1, i + n - 1]
        // 不难发现，随着窗口的逐步右移，每一位公共部分的权值系数都会进行减一
        // nums[i + 1] * 1 + nums[i + 2] * 2 + ... + nums[i + n - 1] * (n - 1)
        // 变为
        // nums[i + 1] * 0 + nums[i + 2] * 1 + ... + nums[i + n - 1] * (n - 2)
        // 因此可以使用前缀和进行优化

        int n = nums.length;
        int[] sum = new int[n * 2 + 10];
        
        for (int i = 1; i <= 2 * n; i++) 
            sum[i] = sum[i - 1] + nums[(i - 1) % n];
        
        int res = 0;

        for (int i = 1; i <= n; i++) 
            res += nums[i - 1] * (i - 1);
        
        for (int i = n + 1, cur = res; i < 2 * n; i++) {
            cur += nums[(i - 1) % n] * (n - 1);
            cur -= sum[i - 1] - sum[i - n];
            if (cur > res) 
                res = cur;
        }
        
        return res;
    }
}
