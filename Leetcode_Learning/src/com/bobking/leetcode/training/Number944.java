package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-03-05 11:10
 */
public class Number944 {

    // 参考：https://leetcode.cn/problems/delete-columns-to-make-sorted/solution/by-ac_oier-smoz/
    public int minDeletionSize(String[] strs) {

        int n = strs.length;
        int m = strs[0].length();
        int ans = 0;
        out:
            for (int i = 0; i < m; i++) {
                for (int j = 0, cur = -1; j < n; j++) {
                    int t = (int) strs[j].charAt(i);
                    if (t < cur) {
                        ++ans;
                        continue out;
                    }

                    cur = t;
                }
            }
        return ans;
    }
}
