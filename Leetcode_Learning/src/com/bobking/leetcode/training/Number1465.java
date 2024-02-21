package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2024/2/21 23:24
 * @Author: BobKing
 * @Description:
 */
public class Number1465 {

    private final int MOD = (int) 1e9 + 7;

    // 参考: https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/solutions/2500353/gong-shui-san-xie-noxiang-xin-ke-xue-xi-ecnvl/?envType=daily-question&envId=2024-02-21
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int n = horizontalCuts.length;
        int m = verticalCuts.length;
        int mh = Math.max(horizontalCuts[0], h - horizontalCuts[n - 1]);
        int mv = Math.max(verticalCuts[0], w - verticalCuts[m - 1]);
        for (int i = 1; i < n; i++)
            mh = Math.max(mh, horizontalCuts[i] - horizontalCuts[i - 1]);
        for (int i = 1; i < m; i++)
            mv = Math.max(mv, verticalCuts[i] - verticalCuts[i - 1]);
        return (int) ((mh * 1L * mv) % MOD);
    }

}
