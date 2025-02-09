package com.bobking.leetcode.training;

/**
 * @Date: 2025/2/9 12:16
 * @Author: BobKing
 * @Description:
 */
public class Number3200 {

    // 参考: https://leetcode.cn/problems/maximum-height-of-a-triangle/solutions/2826643/o1-shu-xue-gong-shi-pythonjavacgo-by-end-t2ht/?envType=daily-question&envId=2025-02-09
    public int maxHeightOfTriangle(int red, int blue) {
        int[] cnt = new int[2];
        for (int i = 1; ; i++) {
            cnt[i % 2] += i;
            if ((cnt[0] > red || cnt[1] > blue) && (cnt[0] > blue || cnt[1] > red)) {
                return i - 1;
            }
        }
    }

}
