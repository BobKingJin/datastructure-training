package com.bobking.leetcode.training;

import java.util.*;

public class Number532 {

    // 参考：https://leetcode.cn/problems/k-diff-pairs-in-an-array/solution/by-ac_oier-ap3v/
    public int findPairs1(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        int ans = 0;

        for (int i : nums) {

            if (map.get(i) == 0)
                continue;

            if (k == 0) {
                if (map.get(i) > 1)
                    ans++;
            } else {
                int a = i - k;
                int b = i + k;
                if (map.getOrDefault(a, 0) > 0)
                    ans++;
                if (map.getOrDefault(b, 0) > 0)
                    ans++;
            }
            // 因为要找到 不同的 k-diff 数对
            map.put(i, 0);
        }

        return ans;
    }

    int[] cnt = new int[10010];

    // 参考：https://leetcode.cn/problems/k-diff-pairs-in-an-array/solution/by-ac_oier-ap3v/
    public int findPairs2(int[] nums, int k) {

        // 对于 x = nums[i] 而言，每次都在整段的 list 中二分找 a = nums[i] - k 和 b = nums[i] + k 的下标
        // 导致统计 nums[i] 对答案的贡献时复杂度为 O(logn)，统计所有 nums[i] 对答案的贡献为 O(nlogn)
        // 实际上，利用 list 本身的有序性，在从前往后处理每个 nums[idx] 时，对应的 a 和 b 也必然是逐步增大
        // 因此可以使用「双指针」来避免「二分」操作，使得统计所有 nums[i] 对答案贡献的复杂度从 O(nlogn) 下降到 O(n)

        Arrays.sort(nums);
        List<Integer> list = new ArrayList<Integer>();

        for (int i : nums) {
            //                    去重
            if (list.isEmpty() || i != list.get(list.size() - 1))
                list.add(i);
        }
        // 例如 nums：4 6 6 8 9 10 10 12 15
        // 那么 list为：4 6 8 9 10 12 15
        // 而 count[4] = 1 count[6] = 2 count[8] = 1 count[9] = 1 count[10] = 2 count[12] = 1 count[15] = 1
        Arrays.fill(cnt, 0);
        for (int i = 0, j = 0; i < nums.length; i++) {

            if (nums[i] != list.get(j))
                j++;

            cnt[j]++;
        }

        int n = list.size();
        int idx = 0;
        int ans = 0;
        int l = 0;
        int r = 0;

        for (int i : list) {

            if (k == 0) {
                if (cnt[idx] > 1)
                    ans++;
            } else {
                int a = i - k;
                int b = i + k;
                while (l < n && list.get(l) < a)
                    l++;
                while (r < n && list.get(r) < b)
                    r++;
                if (l < n && list.get(l) == a && cnt[l] > 0)
                    ans++;
                if (r < n && list.get(r) == b && cnt[r] > 0)
                    ans++;
            }

            cnt[idx++] = 0;
        }

        return ans;
    }
}
