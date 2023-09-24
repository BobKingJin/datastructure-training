package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-09 17:12
 */
public class Number556 {

    // 参考：https://leetcode.cn/problems/next-greater-element-iii/solution/by-ac_oier-99bj/
    public int nextGreaterElement(int n) {

        // 可以先对 x 诸位取出，存成 nums 数组（数组的首位元素为原数 x 的最低位）
        List<Integer> nums = new ArrayList<Integer>();
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }

        int size = nums.size();
        int idx = -1;
        for (int i = 0; i < size - 1 && idx == -1; i++) {
            if (nums.get(i + 1) < nums.get(i))
                idx = i + 1;
        }

        if (idx == -1)
            return -1;

        for (int i = 0; i < idx; i++) {
            if (nums.get(i) > nums.get(idx)) {
                swap(nums, i, idx);
                break;
            }
        }

        for (int l = 0, r = idx - 1; l < r; l++, r--)
            swap(nums, l, r);

        long ans = 0;

        for (int i = size - 1; i >= 0; i--)
            ans = ans * 10 + nums.get(i);

        return ans > Integer.MAX_VALUE ? -1 : (int)ans;
    }

    private void swap(List<Integer> nums, int a, int b) {
        int c = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, c);
    }
}
