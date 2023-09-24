package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-06-10 22:55
 */
public class Number368 {

    // 参考：https://leetcode.cn/problems/largest-divisible-subset/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-0a3jc/
    public List<Integer> largestDivisibleSubset(int[] nums) {

        Arrays.sort(nums);
        int n = nums.length;
        // 类似最长上升子序列
        // 定义 f[i] 为考虑前 i 个数字，且以第 i 个数为结尾的最长「整除子集」长度
        // 如果在 i 之前找不到符合条件 nums[i] % nums[j] == 0 的位置 j，那么 nums[i] 不能接在位置 i 之前的任何数的后面
        // 只能自己独立作为「整除子集」的第一个数，此时状态转移方程为 f[i] = 1
        // 如果在 i 之前能够找到符合条件的位置 j，则取所有符合条件的 f[j] 的最大值，代表如果希望找到以 nums[i] 为结尾的最长「整除子集」
        // 需要将 nums[i] 接到符合条件的最长的 nums[j] 后面，此时状态转移方程为 f[i] = f[j] + 1
        int[] f = new int[n];
        // 见下面可知，g[] 用于回溯
        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            // 至少包含自身一个数，因此起始长度为 1，由自身转移而来
            int len = 1;
            int prev = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    // 如果能接在更长的序列后面，则更新「最大长度」&「从何转移而来」
                    if (f[j] + 1 > len) {
                        len = f[j] + 1;
                        prev = j;
                    }
                }
            }
            // 记录「最终长度」&「从何转移而来」
            f[i] = len;
            g[i] = prev;
        }

        // 遍历所有的 f[i]，取得「最大长度」和「对应下标」
        int max = -1;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (f[i] > max) {
                idx = i;
                max = f[i];
            }
        }

        // 使用 g[] 数组回溯出具体方案
        List<Integer> ans = new ArrayList<Integer>();
        while (ans.size() != max) {
            ans.add(nums[idx]);
            idx = g[idx];
        }

        return ans;
    }
}
