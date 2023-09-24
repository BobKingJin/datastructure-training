package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-10-22 20:22
 */
public class Number1235 {

    // 参考：https://leetcode.cn/problems/maximum-profit-in-job-scheduling/solution/dong-tai-gui-hua-er-fen-cha-zhao-you-hua-zkcg/
    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;
        int[][] jobs = new int[n][n];
        for (int i = 0; i < n; ++i)
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};

        // 按照结束时间排序
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            int j = search(jobs, i, jobs[i][0]);
            f[i + 1] = Math.max(f[i], f[j + 1] + jobs[i][2]);
        }
        return f[n];
    }

    // 返回 endTime <= upper 的最大下标
    private int search(int[][] jobs, int right, int upper) {
        int left = -1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (jobs[mid][1] <= upper) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 参考：https://leetcode.cn/problems/maximum-profit-in-job-scheduling/solution/by-ac_oier-rgup/
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;
        List<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < n; i++)
            list.add(new int[]{startTime[i], endTime[i], profit[i]});

        Collections.sort(list, (a, b) -> a[1] - b[1]);
        int[] f = new int[n + 10];

        for (int i = 1; i <= n; i++) {
            int[] info = list.get(i - 1);
            int a = info[0];
            int c = info[2];
            // 选第 i 份工作
            f[i] = Math.max(f[i - 1], c);

            int l = 0;
            int r = i - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (list.get(mid)[1] <= a) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }

            if (list.get(r)[1] <= a)
                f[i] = Math.max(f[i], f[r + 1] + c);
        }

        return f[n];
    }
}
