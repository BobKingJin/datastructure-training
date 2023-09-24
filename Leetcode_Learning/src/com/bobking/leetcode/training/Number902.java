package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-10-18 9:01
 */
public class Number902 {

    private int[] nums;

    private int dp(int x) {

        List<Integer> list = new ArrayList<Integer>();
        while (x != 0) {
            list.add(x % 10);
            x /= 10;
        }

        int n = list.size();
        int m = nums.length;
        int ans = 0;
        // 位数和 x 相同
        for (int i = n - 1, p = 1; i >= 0; i--, p++) {
            int cur = list.get(i);
            int l = 0;
            int r = m - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (nums[mid] <= cur) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            if (nums[r] > cur) {
                break;
            } else if (nums[r] == cur) {
                ans += r * (int) Math.pow(m, (n - p));
                if (i == 0)
                    ans++;
            } else if (nums[r] < cur) {
                ans += (r + 1) * (int) Math.pow(m, (n - p));
                break;
            }
        }
        // 位数比 x 少的
        for (int i = 1, last = 1; i < n; i++) {
            int cur = last * m;
            ans += cur;
            last = cur;
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/solution/by-ac_oier-8k27/
    public int atMostNGivenDigitSet(String[] digits, int n) {

        int m = digits.length;
        nums = new int[m];

        for (int i = 0; i < m; i++)
            nums[i] = Integer.parseInt(digits[i]);

        return dp(n);
    }
}
