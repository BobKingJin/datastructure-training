package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-04 17:57
 */
public class Number307 {

    // 树状数组概念参考：https://leetcode-cn.com/problems/range-sum-query-mutable/solution/-by-hu-ge-8-t4rn/
    // 参考：https://leetcode-cn.com/problems/range-sum-query-mutable/solution/guan-yu-ge-lei-qu-jian-he-wen-ti-ru-he-x-41hv/
    private class NumArray {

        int[] tree;

        // 找到 x 的二进制数的最后一个 1 所表示的二进制
        int lowbit(int x) {
            return x & -x;
        }

        // 算出前缀和 例如：preSum(7) = s(7) + s(6) + s(4)
        int query(int x) {

            int res = 0;
            for (int i = x; i > 0; i -= lowbit(i)) 
                res += tree[i];

            return res;
        }

        void add(int x, int u) {

            for (int i = x; i <= n; i += lowbit(i))
                tree[i] += u;
        }

        int[] nums;
        int n;

        public NumArray(int[] nums) {
            
            this.nums = nums;
            this.n = nums.length;
            this.tree = new int[n + 1];

            for (int i = 0; i < n; i++)
                add(i + 1, nums[i]);
        }

        public void update(int i, int val) {
            add(i + 1, val - nums[i]);
            nums[i] = val;
        }

        public int sumRange(int l, int r) {
            return query(r + 1) - query(l);
        }
    }
}
