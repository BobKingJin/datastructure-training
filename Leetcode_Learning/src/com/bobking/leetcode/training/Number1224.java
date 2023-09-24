package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number1224 {

    int[] cnt = new int[100010];

    // 使用 sum 来记录出现次数为某个值的数有多少个（即 sum[i] = x 含义为出现次数为 i 的数值共有 x 个）
    // 同时使用 max 来记录最大出现次数 cnt[i]

    // 假设当前处理到的数值为 t = nums[i]（前缀所含数量为 len = i + 1）有如下几种情况，可以更新最大长度 ans
    // max = 1：说明当前处理到的所有 nums[i] 均只出现了一次，此时任意删除一个均满足要求
    // max × sum[max] + 1 = len：说明有一个数值单独出现了一次（其余出现次数为 max 的数值 + 被删除的一个数 = 总数量 len），删除后即满足要求
    // (max − 1) × (sum[max − 1] + 1) + 1 = len：说明出现次数为 max 的数值只有一个，其余出现次数均为 max - 1
    // 对其删除一次后即满足要求（删除该出现次数为 max 的数值后，会导致出现次数为 max - 1 的数值多一个
    // 此时有「出现次数为 max - 1 的数值 + 被删除的一个数 = 总数量 lenl」）

    int[] sum = new int[100010];

    // 参考：https://leetcode.cn/problems/maximum-equal-frequency/solution/by-ac_oier-fviv/
    public int maxEqualFreq(int[] nums) {

        Arrays.fill(cnt, 0);
        Arrays.fill(sum, 0);
        int n = nums.length;
        int max = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            int t = nums[i];
            int cur = ++cnt[t];
            int len = i + 1;
            sum[cur]++;
            sum[cur - 1]--;
            max = Math.max(max, cur);

            if (max == 1)
                ans = len;

            if (max * sum[max] + 1 == len)
                ans = len;

            if ((max - 1) * (sum[max - 1] + 1) + 1 == len)
                ans = len;
        }

        return ans;
    }
}
