package com.bobking.leetcode.training;

/**
 * @Date: 2025/8/31 11:58
 * @Author: BobKing
 * @Description:
 */
public class Number3440 {

    private int eventTime;
    private int[] startTime;
    private int[] endTime;

    // 参考: https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-ii/solutions/3061629/wei-hu-qian-san-da-de-kong-wei-mei-ju-fe-xm2f/?envType=daily-question&envId=2025-08-31
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        this.eventTime = eventTime;
        this.startTime = startTime;
        this.endTime = endTime;
        int n = startTime.length;

        // 有 n + 1 个空位, 计算前三大的空位在哪
        int a = 0;
        int b = -1;
        int c = -1;
        for (int i = 1; i <= n; i++) {
            int sz = get(i);
            if (sz > get(a)) {
                c = b;
                b = a;
                a = i;
            } else if (b < 0 || sz > get(b)) {
                c = b;
                b = i;
            } else if (c < 0 || sz > get(c)) {
                c = i;
            }
        }

        int ans = 0;
        // 枚举桌子
        for (int i = 0; i < n; i++) {
            int sz = endTime[i] - startTime[i];
            // 可以移出去
            if (i != a && i + 1 != a && sz <= get(a) ||
                i != b && i + 1 != b && sz <= get(b) ||
                sz <= get(c)) {
                ans = Math.max(ans, get(i) + sz + get(i + 1));
            } else {
                ans = Math.max(ans, get(i) + get(i + 1));
            }
        }
        return ans;
    }

    // 计算空位长度
    private int get(int i) {
        if (i == 0) {
            return startTime[0];
        }
        int n = startTime.length;
        if (i == n) {
            return eventTime - endTime[n - 1];
        }
        return startTime[i] - endTime[i - 1];
    }


}
