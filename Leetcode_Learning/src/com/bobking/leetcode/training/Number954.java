package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-09-11 21:19
 */
public class Number954 {

    // 由于 arr[i] 的数值范围为 [-10^5, 10^5]，同时存在乘 2 操作，因此需要对计算结果进行 2 * 10^5 的偏移操作，确保其为正数
    // 因为使用数组进行计数的，而数组的角标是不能为负的，所以对于负数必须进行移位操作
    int N = 100010;
    int M = N * 2;
    int[] cnts = new int[M * 2];

    // 参考：https://leetcode.cn/problems/array-of-doubled-pairs/solution/by-ac_oier-d1z7/
    public boolean canReorderDoubled(int[] arr) {

        // 可以借助优先队列（堆）来实现，构造一个以与 0 值距离作为基准的小根堆。每次从堆中取出元素 x，根据当前元素 x 是否被「预定」过进行分情况讨论：
        // 当前值 x 没有被预定过，说明 x 必然是数对中的「绝对值」的较小值，此时给 x 并预定一个 x * 2，即对 x * 2 的预定次数加一
        // 当前值 x 已经被预定过，说明 x 和此前的某个数 x / 2 组成过数对，对 x 的预定次数减一
        // 当且仅当构成过程结束后，所有数的「预定」次数为 0 时，arr 可以凑成 n / 2 组形如 (x, 2 * x) 的数对

        Arrays.fill(cnts, 0);
        // 小根堆
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> Math.abs(a) - Math.abs(b));

        for (int i : arr)
            q.add(i);

        while (!q.isEmpty()) {
            int x = q.poll();
            int t = x * 2;
            if (cnts[x + M] != 0 && --cnts[x + M] >= 0)
                continue;
            cnts[t + M]++;
        }

        for (int i = 0; i < M * 2; i++) {
            if (cnts[i] != 0)
                return false;
        }

        return true;
    }
}
