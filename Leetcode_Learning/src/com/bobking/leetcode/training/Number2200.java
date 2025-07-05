package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2025/7/5 16:52
 * @Author: BobKing
 * @Description:
 */
public class Number2200 {

    // 参考: https://leetcode.cn/problems/find-all-k-distant-indices-in-an-array/solutions/1332985/mo-ni-by-endlesscheng-57j9/?envType=daily-question&envId=2025-07-05
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        // 保证 key 不存在时 last < i - k
        int last = -k - 1;
        for (int i = k - 1; i >= 0; i--) {
            if (nums[i] == key) {
                last = i;
                break;
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (i + k < nums.length && nums[i + k] == key) {
                last = i + k;
            }
            // key 在窗口中
            if (last >= i - k) {
                ans.add(i);
            }
        }
        return ans;
    }
}
