package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-17 22:27
 */
public class Number2597 {

    private int[] nums;
    private int[] cnt;
    private int k;
    private int ans = -1;

    // 参考：https://leetcode.cn/problems/the-number-of-beautiful-subsets/solution/tao-lu-zi-ji-xing-hui-su-pythonjavacgo-b-fcgs/
    public int beautifulSubsets(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        // 用数组实现比哈希表更快
        cnt = new int[k * 2 + 1001];
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == nums.length) {
            ans++;
            return;
        }
        // 不选 位置 i 上面的数
        dfs(i + 1);
        // 避免负数下标
        int x = nums[i] + k;
        // 在选择 x = nums[i] 的时候, 如果之前选过 x − k 或 x + k，则不能选, 否则可以选
        if (cnt[x - k] == 0 && cnt[x + k] == 0) {
            ++cnt[x];
            dfs(i + 1);
            --cnt[x];
        }
    }
}
