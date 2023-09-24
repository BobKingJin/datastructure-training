package com.bobking.leetcode.training;

public class Number327 {

    // 参考：https://leetcode.cn/problems/count-of-range-sum/solution/qu-jian-he-de-ge-shu-by-leetcode-solution/
    //      https://leetcode.cn/problems/count-of-range-sum/solution/xian-ren-zhi-lu-ru-he-xue-xi-ke-yi-jie-jue-ben-ti-/
    public int countRangeSum(int[] nums, int lower, int upper) {

        // 归并排序：
        // 设前缀和数组为 preSum，则问题等价于求所有的下标对 (i, j)
        // 满足 preSum[j] − preSum[i] ∈ [lower, upper]
        // 先考虑如下的问题：给定两个升序排列的数组 n1, n2 试找出所有的下标对 (i, j)，满足
        // n2[j] - n1[i] ∈ [lower, upper]
        // 在已知两个数组均为升序的情况下，这一问题是相对简单的
        // 在 n2 中维护两个指针 l, r 起初，它们都指向 n2 的起始位置
        // 随后，考察 n1 的第一个元素。首先，不断地将指针 l 向右移动
        // 直到 n2[l] ≥ n1[0] + lower 为止，此时，l 及其右边的元素均大于或等于 n1[0] + lower
        // 随后，再不断地将指针 r 向右移动，直到 n2[r] > n1[0] + upper 为止，则 r 左边的元素均小于或等于 n1[0] + upper
        // 故区间 [l, r) 中的所有下标 j，都满足 n2[j] - n1[0] ∈ [lower, upper]
        // 接下来，考察 n1 的第二个元素。由于 n1 是递增的，不难发现 l, r 只可能向右移动。因此，不断地进行上述过程，
        // 并对于 n1 中的每一个下标，都记录相应的区间 [l, r) 的大小。最终，就统计得到了满足条件的下标对 (i, j) 的数量
        //
        // 在解决这一问题后，原问题就迎刃而解了：采用归并排序的方式，能够得到左右两个数组排序后的形式，
        // 以及对应的下标对数量。对于原数组而言，若要找出全部的下标对数量，只需要再额外找出左端点在左侧数组，
        // 同时右端点在右侧数组的下标对数量，而这正是此前讨论的问题

        long s = 0;
        // 前缀和
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }

        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    // 归并排序
    private int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {

                while (l <= right && sum[l] - sum[i] < lower)
                    l++;

                while (r <= right && sum[r] - sum[i] <= upper)
                    r++;

                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            long[] sorted = new long[right - left + 1];
            int p1 = left;
            int p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = sum[p1++];
                    } else {
                        sorted[p++] = sum[p2++];
                    }
                }
            }

            for (int j = 0; j < sorted.length; j++)
                sum[left + j] = sorted[j];

            return ret;
        }
    }

}
