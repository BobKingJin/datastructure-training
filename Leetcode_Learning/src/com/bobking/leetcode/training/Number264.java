package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-06-04 20:17
 */
public class Number264 {

    int[] nums = new int[]{2, 3, 5};

    // 参考：https://leetcode.cn/problems/ugly-number-ii/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-3nvs/
    public int nthUglyNumber1(int n) {

        // 根据丑数的定义，有如下结论:
        // 1 是最小的丑数
        // 对于任意一个丑数 x，其与任意的质因数（2、3、5）相乘，结果（2x、3x、5x）仍为丑数

        // 起始先将最小丑数 1 放入队列
        // 每次从队列取出最小值 x，然后将 x 所对应的丑数 2x、3x 和 5x 进行入队
        Set<Long> set = new HashSet<Long>();
        Queue<Long> pq = new PriorityQueue<Long>();
        set.add(1L);
        pq.add(1L);
        for (int i = 1; i <= n; i++) {
            long x = pq.poll();
            if (i == n)
                return (int)x;
            for (int num : nums) {
                long t = num * x;
                if (!set.contains(t)) {
                    set.add(t);
                    pq.add(t);
                }
            }
        }
        return -1;
    }

    // 参考：https://leetcode.cn/problems/ugly-number-ii/solution/gong-shui-san-xie-yi-ti-shuang-jie-you-x-3nvs/
    public int nthUglyNumber2(int n) {

        // ans 用作存储已有丑数（从下标 1 开始存储，第一个丑数为 1）
        int[] ans = new int[n + 1];
        ans[1] = 1;

        // 由于三个有序序列都是由「已有丑数」*「质因数」而来
        // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
        for (int i2 = 1, i3 = 1, i5 = 1, idx = 2; idx <= n; idx++) {
            // 由 ans[iX] * X 可得当前有序序列指向哪一位
            int a = ans[i2] * 2;
            int b = ans[i3] * 3;
            int c = ans[i5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            int min = Math.min(a, Math.min(b, c));
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过（不能使用 else if ）
            if (min == a)
                i2++;
            if (min == b)
                i3++;
            if (min == c)
                i5++;
            ans[idx] = min;
        }

        return ans[n];
    }
}
