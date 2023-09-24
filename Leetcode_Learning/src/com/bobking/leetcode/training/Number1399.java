package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-26 7:20
 */
public class Number1399 {

    public int countLargestGroup(int n) {

        int ans = 0;
        int max = 1;
        int[] count = new int[n +  1];
        // 计算 1 - n 各个元素的数位和，例如数字 i 的数位和是 sum[i / 10] + i % 10
        int[] sum = new int[n + 1];

        for(int i = 1; i <= n; i++){
            sum[i] = sum[i / 10] + i % 10;
            count[sum[i]]++;
            if(count[sum[i]] > max)
                max = count[sum[i]];
        }

        for(int num : count)
            ans += num == max ? 1 : 0;

        return ans;
    }
}
