package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-11 11:45
 */
public class Number1283 {

    // 参考：https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold/solution/er-fen-cha-zhao-ding-wei-chu-shu-by-liweiwei1419/
    public int smallestDivisor(int[] nums, int threshold) {

        // 先找数组中的最大值，用最大值作为除数，除完以后和最小
        int maxVal = 1;
        for (int num : nums)
            maxVal = Math.max(maxVal, num);

        // 一开始以为最小就是数组中最小的那个数字，后来提交以后，发现测试用例：
        // int[] nums = {91, 41, 78, 86, 8};
        // int threshold = 114;
        // 不能通过。于是才知道，原来 threshold 可以很大，因此除数的最小值可以让它是 1
        // 因为二分查找很快，除数的下限小一点是可以的，只要包含目标值就行
        // 注意：最小值是 1，因为 threshold 可以很大
        int left = 1;
        int right = maxVal;

        while (left < right) {

            int mid = (left + right) >>> 1;

            if (calculateSum(nums, mid) > threshold) {
                // sum 大于阈值一定不是解，说明除数选得太小了
                // 下一轮搜索区间是 [mid + 1, right]
                // （把下一轮搜索区间写出来，边界选择就不会错）
                left = mid + 1;
                // 边界是 left = mid + 1 ，中间数不用上取整
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int calculateSum(int[] nums, int divisor) {

        int sum = 0;

        for (int num : nums) {
            sum += num / divisor;
            // 注意：不能整除的时候，需要向上取整
            if (num % divisor != 0)
                sum++;
        }
        return sum;
    }

}
