package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-09-24 20:18
 */
public class Number825 {

    // 参考：https://leetcode.cn/problems/friends-of-appropriate-ages/solution/gong-shui-san-xie-yi-ti-shuang-jie-pai-x-maa8/
    public int numFriendRequests(int[] ages) {

        Arrays.sort(ages);
        int n = ages.length;
        int ans = 0;

        // 可以先对 ages 进行排序，枚举每个 y = ages[k]
        // 同时使用 i 和 j 维护左右区间，[i, j) 代表在 ages 上会往 y = ages[k] 发送请求的 x 连续段
        // 统计每个 y = ages[k] 的 x 有多少个即是答案，同时需要注意在 [i, j) 范围内是包含 y = ages[k] 自身，统计区间长度时需要进行 -1 操作

        for (int k = 0, i = 0, j = 0; k < n; k++) {

            while (i < k && !check(ages[i], ages[k]))
                i++;

            if (j < k)
                j = k;

            while (j < n && check(ages[j], ages[k]))
                j++;

            if (j > i)
                ans += j - i - 1;
        }

        return ans;
    }

    private boolean check(int x, int y) {

        if (y <= 0.5 * x + 7)
            return false;

        if (y > x)
            return false;

        if (y > 100 && x < 100)
            return false;

        return true;
    }

}
