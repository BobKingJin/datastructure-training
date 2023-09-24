package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-09-16 21:08
 */
public class Number850 {

    int MOD = (int)1e9+7;

    // 参考：https://leetcode.cn/problems/rectangle-area-ii/solution/gong-shui-san-xie-by-ac_oier-9r36/
    public int rectangleArea(int[][] rectangles) {

        List<Integer> list = new ArrayList<Integer>();
        for (int[] info : rectangles) {
            list.add(info[0]);
            list.add(info[2]);
        }

        Collections.sort(list);
        long ans = 0;

        for (int i = 1; i < list.size(); i++) {
            int a = list.get(i - 1);
            int b = list.get(i);
            int len = b - a;

            if (len == 0)
                continue;

            List<int[]> lines = new ArrayList<int[]>();
            for (int[] info : rectangles) {
                if (info[0] <= a && b <= info[2])
                    lines.add(new int[]{info[1], info[3]});
            }

            Collections.sort(lines, (l1, l2)->{
                return l1[0] != l2[0] ? l1[0] - l2[0] : l1[1] - l2[1];
            });

            long tot = 0;
            int l = -1;
            int r = -1;

            for (int[] cur : lines) {
                if (cur[0] > r) {
                    tot += r - l;
                    l = cur[0];
                    r = cur[1];
                } else if (cur[1] > r) {
                    r = cur[1];
                }
            }
            tot += r - l;
            ans += tot * len;
            ans %= MOD;
        }

        return (int) ans;
    }
}
