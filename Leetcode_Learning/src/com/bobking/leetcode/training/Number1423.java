package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-07-30 0:19
 */
public class Number1423 {

    // 参考：https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/solution/ke-huo-de-de-zui-da-dian-shu-by-leetcode-7je9/
    public int maxScore(int[] cardPoints, int k) {

        // 记数组 cardPoints 的长度为 n，由于只能从开头和末尾拿 k 张卡牌，所以最后剩下的必然是连续的 n - k 张卡牌
        // 可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值
        // 由于剩余卡牌是连续的，使用一个固定长度为 n - k 的滑动窗口对数组 cardPoints 进行遍历，求出滑动窗口最小值，然后用所有卡牌的点数之和减去该最小值
        // 即得到了拿走卡牌点数之和的最大值。

        int n = cardPoints.length;
        // 滑动窗口大小为 n - k
        int windowSize = n - k;
        // 选前 n - k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i)
            sum += cardPoints[i];

        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }

        return Arrays.stream(cardPoints).sum() - minSum;
    }
}
