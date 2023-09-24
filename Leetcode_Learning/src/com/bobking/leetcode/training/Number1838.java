package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-09-23 21:41
 */
public class Number1838 {

    // 参考：https://leetcode.cn/problems/frequency-of-the-most-frequent-element/solution/gong-shui-san-xie-cong-mei-ju-dao-pai-xu-kxnk/
    public int maxFrequency1(int[] nums, int k) {

        // 一个朴素的做法是，先对原数组 nums 进行排序，然后枚举最终「频数对应值」是哪个
        // 利用每次操作只能对数进行加一，可以从「频数对应值」开始往回检查
        // 从而得出在操作次数不超过 k 的前提下，以某个值作为「频数对应值」最多能够凑成多少个

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        List<Integer> list = new ArrayList<Integer>(map.keySet());
        // 排序
        Collections.sort(list);
        int ans = 1;

        for (int i = 0; i < list.size(); i++) {

            // 将 i 之前的数，通过增加，变为 i
            int x = list.get(i);
            int cnt = map.get(x);

            if (i > 0) {
                int p = k;
                for (int j = i - 1; j >= 0; j--) {
                    int y = list.get(j);
                    int diff = x - y;
                    if (p >= diff) {
                        int add = p / diff;
                        int min = Math.min(map.get(y), add);
                        p -= min * diff;
                        cnt += min;
                    } else {
                        break;
                    }
                }

            }
            ans = Math.max(ans, cnt);
        }

        return ans;
    }

    int[] nums;
    int[] sum;
    int n;
    int k;

    // 参考：https://leetcode.cn/problems/frequency-of-the-most-frequent-element/solution/gong-shui-san-xie-cong-mei-ju-dao-pai-xu-kxnk/
    public int maxFrequency2(int[] nums, int k) {

        // 先对原数组 nums 进行从小到大排序，如果存在真实最优解 len，意味着至少存在一个大小为 len 的区间 [l, r]
        // 使得在操作次数不超过 k 的前提下，区间 [l, r] 的任意值 nums[i] 的值调整为 nums[r]

        this.nums = nums;
        this.k = k;
        n = nums.length;
        Arrays.sort(nums);
        sum = new int[n + 1];

        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        int l = 0;
        int r = n;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    // 快速判断「某个区间 [l, r] 是否可以在 k 次操作内将所有值变为 nums[r]
    private boolean check(int len) {

        for (int l = 0; l + len - 1 < n; l++) {

            int r = l + len - 1;
            int cur = sum[r + 1] - sum[l];
            int t = nums[r] * len;

            if (t - cur <= k)
                return true;
        }

        return false;
    }
}
