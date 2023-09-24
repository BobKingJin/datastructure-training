package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-02-04 10:42
 */
public class Number1798 {

    // 参考：https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/solution/ni-neng-gou-zao-chu-lian-xu-zhi-de-zui-d-hlxf/
    // 参考：https://leetcode.cn/problems/maximum-number-of-consecutive-values-you-can-make/solution/mei-xiang-ming-bai-yi-zhang-tu-miao-dong-7xlx/
    public int getMaximumConsecutive(int[] coins) {

        Arrays.sort(coins);
        int x = 0;

        for (int y: coins) {
            if (y > x + 1)
                break;
            x += y;
        }
        return x + 1;
    }
}
