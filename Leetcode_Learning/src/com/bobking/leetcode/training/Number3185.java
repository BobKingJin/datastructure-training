package com.bobking.leetcode.training;

/**
 * @Date: 2025/3/16 11:43
 * @Author: BobKing
 * @Description:
 */
public class Number3185 {

    private final int TWENTY_FOUR = 24;

    // 参考: https://leetcode.cn/problems/count-pairs-that-form-a-complete-day-ii/solutions/2812385/tao-lu-mei-ju-you-wei-hu-zuo-pythonjavac-3vhv/?envType=daily-question&envId=2025-03-16
    public long countCompleteDayPairs(int[] hours) {
        long ans = 0;
        int[] cnt = new int[TWENTY_FOUR];
        for (int t : hours) {
            // 先查询 cnt，再更新 cnt，因为题目要求 i < j
            // 如果先更新，再查询，就把 i = j 的情况也考虑进去了
            ans += cnt[(TWENTY_FOUR - t % TWENTY_FOUR) % TWENTY_FOUR];
            cnt[t % TWENTY_FOUR]++;
        }
        return ans;
    }

}
