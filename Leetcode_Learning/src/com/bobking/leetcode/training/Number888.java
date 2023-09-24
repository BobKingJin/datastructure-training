package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2023-03-19 11:22
 */
public class Number888 {

    // 参考：https://leetcode.cn/problems/fair-candy-swap/solution/gong-ping-de-tang-guo-jiao-huan-by-leetc-tlam/
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {

        int sumA = Arrays.stream(aliceSizes).sum();
        int sumB = Arrays.stream(bobSizes).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<Integer>();
        for (int num : aliceSizes)
            rec.add(num);

        int[] ans = new int[2];
        for (int y : bobSizes) {
            int x = y + delta;
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }
}
