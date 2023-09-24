package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-30 15:55
 */
public class Number2078 {

    // 参考：https://leetcode.cn/problems/two-furthest-houses-with-different-colors/solution/liang-chong-fang-fa-bao-li-tan-xin-by-li-vlkl/
    public int maxDistance(int[] colors) {

        int length = colors.length;

        // 如果首位颜色不同直接返回
        if (colors[0] != colors[length - 1])
            return length - 1;

        // 获取左边第一个不相同的位置
        int left = 1;
        while (colors[left] == colors[0])
            left++;

        // 获取右边第一个不相同的位置
        int right = length - 2;
        while (colors[right] == colors[length - 1])
            right--;


        // 0～right 的长度 和 left～length - 1 的长度取最大值
        // 因为要最大，所以不可能在中间，要么就是左边，要么就是右边
        return Math.max(right, length - 1 - left);
    }
}
