package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-06-25 10:27
 */
public class Number446 {

    // 参考：https://leetcode.cn/problems/arithmetic-slices-ii-subsequence/solution/gong-shui-san-xie-xiang-jie-ru-he-fen-xi-ykvk/
    public int numberOfArithmeticSlices(int[] nums) {

        int n = nums.length;
        // 最巧妙的位置是用了一个 Map 来包装了 {d : cnt}，从而知道在每一个以 nums[i] 为结尾的，之前出现过的所有差值
        // 每个 f[i] 均为哈希表，哈希表键值对为 {d : cnt}
        // d: 子序列差值
        // cnt: 以 nums[i] 为结尾，且差值为 d 的子序列数量
        List<Map<Long, Integer>> f = new ArrayList<Map<Long, Integer>>();
        for (int i = 0; i < n; i++) {
            Map<Long, Integer> cur = new HashMap<Long, Integer>();
            for (int j = 0; j < i; j++) {
                Long d = nums[i] * 1L - nums[j];
                Map<Long, Integer> prev = f.get(j);
                int cnt = cur.getOrDefault(d, 0);
                cnt += prev.getOrDefault(d, 0);
                cnt++;
                cur.put(d, cnt);
            }
            f.add(cur);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Long, Integer> cur = f.get(i);
            for (Long key : cur.keySet())
                ans += cur.get(key);
        }

        // 计算的 ans 为统计所有的「长度大于 1」的等差子序列数量，由于长度必然为正整数，也就是统计的是「长度大于等于 2」的等差子序列的数量
        // 因此，如果能够求出长度为 2 的子序列的个数的话，从 ans 中减去，得到的就是「长度为至少为 3」子序列的数量
        // 长度为 2 的等差子序列，由于没有第三个数的差值限制，因此任意的数对 (j, i) 都是一个合法的长度为 2 的等差子序列
        // 而求长度为 n 的数组的所有数对，其实就是求 首项为 0，末项为 n - 1，公差为 1，长度为 n 的等差数列之和，直接使用「等差数列求和」公式求解即可
        int a1 = 0;
        int an = n - 1;
        int cnt = (a1 + an) * n / 2;
        return ans - cnt;
    }
}
