package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-01-14 11:13
 */
public class Number1552 {

    // 参考：https://leetcode.cn/problems/magnetic-force-between-two-balls/solution/liang-qiu-zhi-jian-de-ci-li-by-leetcode-solution/
    public int maxDistance(int[] position, int m) {

        Arrays.sort(position);

        int left = 1;
        int right = position[position.length - 1] - position[0];
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, position, m)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int x, int[] position, int m) {

        int pre = position[0];
        int cnt = 1;

        for (int i = 1; i < position.length; ++i) {
            if (position[i] - pre >= x) {
                pre = position[i];
                cnt += 1;
            }
        }

        return cnt >= m;
    }
}
