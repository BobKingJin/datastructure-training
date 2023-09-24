package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-07 10:35
 */
public class Number492 {

    // 参考：https://leetcode.cn/problems/construct-the-rectangle/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-7ser/
    public int[] constructRectangle(int area) {

        for (int i = (int)(Math.sqrt(area)); ;i--) {
            if (area % i == 0)
                return new int[]{area / i, i};
        }
    }
}
