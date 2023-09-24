package com.bobking.leetcode.training;

public class Number1051 {

    // 参考：https://leetcode.cn/problems/height-checker/solution/onjie-fa-yong-shi-yu-nei-cun-ji-bai-100-javayong-h/
    public int heightChecker(int[] heights) {

        // 1 <= heights[i] <= 100
        int[] total = new int[101];
        for (int height : heights)
            total[height]++;

        int count = 0;
        //  i 初始化为 1，是因为 1 <= heights[i] <= 100
        for (int i = 1, j = 0; i < total.length; i++)
            while (total[i]-- > 0) {
                if (heights[j++] != i)
                    count++;
            }

        return count;
    }
}
