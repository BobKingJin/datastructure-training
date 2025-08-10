package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Date: 2025/8/10 0:26
 * @Author: BobKing
 * @Description:
 */
public class Number3318 {

    private final TreeSet<int[]> L = new TreeSet<>(
        (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
    private final TreeSet<int[]> R = new TreeSet<>(L.comparator());
    private final Map<Integer, Integer> cnt = new HashMap<>();
    private long sumL = 0;

    // 参考: https://leetcode.cn/problems/find-x-sum-of-all-k-long-subarrays-ii/description/
    public long[] findXSum(int[] nums, int k, int x) {
        long[] ans = new long[nums.length - k + 1];
        for (int r = 0; r < nums.length; r++) {
            // 添加 in
            int in = nums[r];
            // 先移除
            del(in);
            cnt.merge(in, 1, Integer::sum);
            // 再添加
            add(in);
            int l = r + 1 - k;
            if (l < 0) {
                continue;
            }
            // 维护大小
            while (!R.isEmpty() && L.size() < x) {
                r2l();
            }
            while (L.size() > x) {
                l2r();
            }
            ans[l] = sumL;
            // 移除 out
            int out = nums[l];
            del(out);
            cnt.merge(out, -1, Integer::sum);
            add(out);
        }
        return ans;
    }

    // 添加元素
    private void add(int val) {
        int c = cnt.get(val);
        if (c == 0) {
            return;
        }
        int[] p = new int[]{c, val};
        if (!L.isEmpty() && L.comparator().compare(p, L.first()) > 0) {
            sumL += (long) p[0] * p[1];
            L.add(p);
        } else {
            R.add(p);
        }
    }

    // 删除元素
    private void del(int val) {
        int c = cnt.getOrDefault(val, 0);
        if (c == 0) {
            return;
        }
        int[] p = new int[]{c, val};
        if (L.contains(p)) {
            sumL -= (long) p[0] * p[1];
            L.remove(p);
        } else {
            R.remove(p);
        }
    }

    // 从 L 移动一个元素到 R
    private void l2r() {
        int[] p = L.pollFirst();
        sumL -= (long) p[0] * p[1];
        R.add(p);
    }

    // 从 R 移动一个元素到 L
    private void r2l() {
        int[] p = R.pollLast();
        sumL += (long) p[0] * p[1];
        L.add(p);
    }

}
