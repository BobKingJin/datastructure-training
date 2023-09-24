package com.bobking.leetcode.training;

public class Number274 {

    // 参考：https://leetcode.cn/problems/h-index/solution/gong-shui-san-xie-li-yong-er-duan-xing-z-1jxw/
    public int hIndex(int[] citations) {

        // 需要找到满足条件「引用次数至少为 x 次的 x 篇论文」中的最大 x 值
        // 那么在以最大值 x 为分割点的正整数数轴上，满足二段性：
        // 少于等于 x 的数值必然满足条件
        // 大于 x 的数值必然不满足
        // 因此可以通过二分在 [0, n] 范围内找分割点 x

        int n = citations.length;
        int l = 0;
        int r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(citations, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    private boolean check(int[] citations, int mid) {

        int ans = 0;
        for (int i : citations) {
            if (i >= mid)
                ans++;
        }

        return ans >= mid;
    }
}
