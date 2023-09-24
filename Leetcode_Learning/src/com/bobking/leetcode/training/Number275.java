package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-02 8:54
 */
public class Number275 {

    // 参考：https://leetcode.cn/problems/h-index-ii/solution/gong-shui-san-xie-liang-chong-er-fen-ji-sovjb/
    public int hIndex1(int[] citations) {

        int n = citations.length;
        int l = 0;
        int r = n;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(citations, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private boolean check(int[] cs, int mid) {
        int ans = 0;
        for (int i : cs) {
            if (i >= mid)
                ans++;
        }
        return ans >= mid;
    }

    // 参考：https://leetcode.cn/problems/h-index-ii/solution/gong-shui-san-xie-liang-chong-er-fen-ji-sovjb/
    public int hIndex2(int[] citations) {

        // 假设存在真实分割点下标 x，其值大小为 citations[x]，分割点右边的数值个数为 n − x
        // 根据 H 指数 的定义，必然有 citations[x] >= n − x 关系

        int n = citations.length;
        int l = 0;
        int r = n - 1;

        while (l < r) {
            int mid = l + r >> 1;
            if (citations[mid] >= n - mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return citations[r] >= n - r ? n - r : 0;
    }


}
