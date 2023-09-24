package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-16 8:46
 */
public class Number2064 {

    // 参考：https://leetcode.cn/problems/minimized-maximum-of-products-distributed-to-any-store/solution/wei-rao-li-lun-jia-she-jian-yan-xing-er-nn4p0/
    public int minimizedMaximum(int n, int[] quantities) {

        long sum = 0;
        for (int q : quantities)
            sum += q;

        long l = 0;
        long r = sum;

        while (l < r) {
            long mid = (l + r) / 2;
            if (mid == 0)
                return 1;
            if (check(quantities, n, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return (int) l;
    }

    private boolean check(int[] q, int n, long x) {

        // 由于同一个店只能持有一种商品，所以某个商品需要的店数一定为 商品总数 除以 最大分配数 向上取整
        // 基于贪心的策略，去看分配的店数量是否满足条件即可
        // 然后在进一步缩小搜索空间，直到找到最小的
        int nn = 0;
        for (int cnt : q) {
            nn += cnt / x;
            if (cnt % x != 0)
                nn++;
        }
        if (nn > n)
            return false;
        return true;
    }
}
