package com.bobking.leetcode.training;

/**
 * @Date: 2023/12/10 6:32
 * @Author: BobKing
 * @Description:
 */
public class LCS01 {

    // 参考: https://leetcode.cn/problems/Ju9Xwi/solutions/835341/di-yi-ci-can-jia-bi-sai-xie-ge-ti-jie-ji-crdd/
    public int leastMinutes(int n) {

        int per = 1;
        int ans = 0;

        while (n > 0) {
            if (n <= per) {
                ans += 1;
                n -= per;
            } else {
                ans += 1;
                per *= 2;
            }
        }
        return ans;
    }
}
