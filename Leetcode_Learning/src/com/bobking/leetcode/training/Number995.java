package com.bobking.leetcode.training;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author BobKing
 * @create 2022-07-30 12:16
 */
public class Number995 {

    // 参考：https://leetcode.cn/problems/minimum-number-of-k-consecutive-bit-flips/solution/hua-dong-chuang-kou-shi-ben-ti-zui-rong-z403l/
    public int minKBitFlips(int[] nums, int k) {

        // 滑动窗口的含义是前面 k - 1 个元素中，以哪些位置起始的 子区间 进行了翻转
        // 该滑动窗口从左向右滑动，如果当前位置 i 需要翻转，则把该位置存储到队列中
        // 遍历到新位置 j (j < i + k) 时，队列中元素的个数代表了 i 被前面 k - 1 个元素翻转的次数
        // 当 A[i] 为 0，如果 i 位置被翻转了偶数次，那么翻转后仍是 0，当前元素需要翻转
        // 当 A[i] 为 1，如果 i 位置被翻转了奇数次，那么翻转后变成 0，当前元素需要翻转
        // 综合上面两点，得到一个结论，如果 len(queue) % 2 == A[i] 时，当前元素需要翻转
        // 当 i + k > N 时，说明需要翻转大小为 k 的子区间，但是后面剩余的元素不到 k 个了，所以返回 -1

        int res = 0;
        Deque<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < nums.length; i++) {

            if (queue.size() > 0 && i > queue.peek() + k - 1)
                queue.removeFirst();

            if (queue.size() % 2 == nums[i]) {
                if (i + k > nums.length)
                    return -1;
                queue.add(i);
                res += 1;
            }
        }

        return res;
    }
}
