package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Number1707 {

    // 参考：程序猿代码指南P324
    // 参考：程序猿代码指南P326
    // 参考：https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/solution/gong-shui-san-xie-jie-zhe-ge-wen-ti-lai-lypqr/
    private class Solution1 {

        int N = (int) 1e5 * 32;
        int[][] trie = new int[N][2];
        int idx = 0;

        public Solution1() {
            for (int i = 0; i <= idx; i++)
                Arrays.fill(trie[i], 0);
            idx = 0;
        }

        private void add(int x) {
            int p = 0;
            for (int i = 31; i >= 0; i--) {
                int u = (x >> i) & 1;
                if (trie[p][u] == 0)
                    trie[p][u] = ++idx;
                p = trie[p][u];
            }
        }

        private int getVal(int x) {
            int ans = 0;
            int p = 0;
            for (int i = 31; i >= 0; i--) {
                int a = (x >> i) & 1, b = 1 - a;
                if (trie[p][b] != 0) {
                    p = trie[p][b];
                    ans = ans | (b << i);
                } else {
                    p = trie[p][a];
                    ans = ans | (a << i);
                }
            }
            return ans ^ x;
        }

        public int[] maximizeXor(int[] nums, int[][] qs) {

            int m = nums.length, n = qs.length;

            // 使用哈希表将原本的顺序保存下来
            Map<int[], Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) map.put(qs[i], i);

            // 将 nums 与 queries[x][1] 进行「从小到大」进行排序
            Arrays.sort(nums);
            Arrays.sort(qs, (a, b) -> a[1] - b[1]);

            int[] ans = new int[n];
            int loc = 0; // 记录 nums 中哪些位置之前的数已经放入 Trie
            for (int[] q : qs) {
                int x = q[0], limit = q[1];
                // 将小于等于 limit 的数存入 Trie
                while (loc < m && nums[loc] <= limit)
                    add(nums[loc++]);
                if (loc == 0) {
                    ans[map.get(q)] = -1;
                } else {
                    ans[map.get(q)] = getVal(x);
                }
            }
            return ans;
        }
    }

    // 参考：程序猿代码指南P324
    // 参考：程序猿代码指南P326
    // 参考：https://leetcode-cn.com/problems/maximum-xor-with-an-element-from-array/solution/gong-shui-san-xie-jie-zhe-ge-wen-ti-lai-lypqr/
    private class Solution2 {

        int N = (int) 1e5 * 32;
        int[][] trie = new int[N][2];
        int[] cnt = new int[N];
        int idx = 0;

        public Solution2() {
            for (int i = 0; i <= idx; i++) {
                Arrays.fill(trie[i], 0);
                cnt[i] = 0;
            }
            idx = 0;
        }

        // 往 Trie 存入(v = 1)/删除(v = -1) 某个数 x
        private void add(int x, int v) {
            int p = 0;
            for (int i = 31; i >= 0; i--) {
                int u = (x >> i) & 1;
                if (trie[p][u] == 0) trie[p][u] = ++idx;
                p = trie[p][u];
                cnt[p] += v;
            }
        }

        private int getVal(int x) {
            int ans = 0;
            int p = 0;
            for (int i = 31; i >= 0; i--) {
                int a = (x >> i) & 1, b = 1 - a;
                if (cnt[trie[p][b]] != 0) {
                    p = trie[p][b];
                    ans = ans | (b << i);
                } else if (cnt[trie[p][a]] != 0) {
                    p = trie[p][a];
                    ans = ans | (a << i);
                } else {
                    return -1;
                }
            }
            return ans ^ x;
        }

        public int[] maximizeXor(int[] nums, int[][] qs) {
            int n = qs.length;

            // 使用哈希表将原本的顺序保存下来
            Map<int[], Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) map.put(qs[i], i);

            // 对两者排降序
            sort(nums);
            Arrays.sort(qs, (a, b) -> b[1] - a[1]);

            // 将所有数存入 Trie
            for (int i : nums)
                add(i, 1);

            int[] ans = new int[n];
            int left = -1; // 在 nums 中下标「小于等于」left 的值都已经从 Trie 中移除
            for (int[] q : qs) {
                int x = q[0], limit = q[1];
                // 二分查找到待删除元素的右边界，将其右边界之前的所有值从 Trie 中移除。
                int right = getRight(nums, limit);
                for (int i = left + 1; i < right; i++) add(nums[i], -1);
                left = right - 1;
                ans[map.get(q)] = getVal(x);
            }
            return ans;
        }

        // 二分找到待删除的右边界
        private int getRight(int[] nums, int limit) {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] <= limit) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return nums[r] <= limit ? r : r + 1;
        }

        // 对 nums 进行降序排序（Java 没有 Api 直接支持对基本类型 int 排倒序，其他语言可忽略）
        private void sort(int[] nums) {
            Arrays.sort(nums);
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int c = nums[r];
                nums[r--] = nums[l];
                nums[l++] = c;
            }
        }
    }

}
