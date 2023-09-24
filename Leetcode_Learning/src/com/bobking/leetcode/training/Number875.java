package com.bobking.leetcode.training;

public class Number875 {

    public int minEatingSpeed(int[] piles, int h) {

        // 思路分析：
        // 根据题意：吃香蕉的速度越小，耗时越多。反之，速度越大，耗时越少，这是题目的 单调性
        // 要找的是速度。因为题目限制了一个小时之内只能选择一堆香蕉吃
        // 因此速度最大值就是这几堆香蕉中数量最多的那一堆，速度的最小值是 1
        // 其实还可以再分析一下下界是多少，由于二分搜索的时间复杂度很低，严格的分析不是很有必要
        // 还是因为一个小时之内只能选择一堆香蕉吃，因此：每堆香蕉吃完的耗时 = 这堆香蕉的数量 / 珂珂一小时吃香蕉的数量
        // 根据题意，这里的 / 在不能整除的时候，需要 上取整
        // 注意：当「二分查找」算法猜测的速度恰好使得珂珂在规定的时间内吃完香蕉的时候
        // 还应该去尝试更小的速度是不是还可以保证在规定的时间内吃完香蕉

        int maxVal = 1;
        for (int pile : piles)
            maxVal = Math.max(maxVal, pile);

        // 速度最小的时候，耗时最长
        int left = 1;
        // 速度最大的时候，耗时最短
        int right = maxVal;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (calculateSum(piles, mid) > h) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int calculateSum(int[] piles, int speed) {

        int sum = 0;
        for (int pile : piles)
            // 上取整可以这样写
            sum += (pile + speed - 1) / speed;

        return sum;
    }
}
