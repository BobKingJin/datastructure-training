package com.bobking.leetcode.training;

import java.util.List;

/**
 * @Date: 2025/9/14 14:34
 * @Author: BobKing
 * @Description:
 */
public class Number2848 {

    // 参考: https://leetcode.cn/problems/car-pooling/solutions/2550264/suan-fa-xiao-ke-tang-chai-fen-shu-zu-fu-9d4ra/
    // 参考: https://leetcode.cn/problems/points-that-intersect-with-cars/solutions/2435384/chai-fen-shu-zu-xian-xing-zuo-fa-by-endl-3xpm/
    public int numberOfPoints(List<List<Integer>> nums) {
        int maxEnd = 0;
        for (List<Integer> p : nums) {
            maxEnd = Math.max(maxEnd, p.get(1));
        }
        // 注意是 maxEnd + 2, 而不是 maxEnd + 1, 因为下面是 diff[interval.get(1) + 1]
        int[] diff = new int[maxEnd + 2];
        for (List<Integer> interval : nums) {
            diff[interval.get(0)]++;
            diff[interval.get(1) + 1]--;
        }

        int ans = 0;
        int s = 0;
        for (int d : diff) {
            s += d;
            if (s > 0) {
                ans++;
            }
        }
        return ans;
    }

}
