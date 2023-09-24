package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-20 18:56
 */
public class Number1208 {

    // 参考：https://leetcode.cn/problems/get-equal-substrings-within-budget/solution/ni-bu-ke-neng-kan-bu-dong-de-qian-zhui-h-u4l1/
    public int equalSubstring(String s, String t, int maxCost) {

        int n = s.length();
        s = " " + s;
        t = " " + t;
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        // 先预处理出修改成本的前缀和数组 sum
        // 当有了前缀和数组之后，对于任意区间 [i, j] 的修改成本，便可以通过 sum[j] - sum[i - 1] 得出
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + Math.abs(ss[i] - tt[i]);

        int l = 1;
        int r = n;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(sum, mid, maxCost)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return check(sum, r, maxCost) ? r : 0;
    }

    private boolean check(int[] nums, int mid, int max) {

        for (int i = mid; i < nums.length; i++) {

            int tot = nums[i] - nums[i - mid];

            if (tot <= max)
                return true;
        }

        return false;
    }
}
