package com.bobking.leetcode.training;

public class Number1109 {

    // 参考：https://leetcode.cn/problems/corporate-flight-bookings/solution/5118_hang-ban-yu-ding-tong-ji-by-user9081a/
    public int[] corpFlightBookings(int[][] bookings, int n) {

        // 换一种思路理解题意，将问题转换为：某公交车共有 n 站
        // 第 i 条记录 bookings[i] = [i, j, k] 表示在 i 站上车 k 人，乘坐到 j 站，在 j + 1 站下车
        // 需要按照车站顺序返回每一站车上的人数
        // 根据上述的思路，定义 counter[] 数组记录每站的人数变化，counter[i] 表示第 i + 1 站
        // 遍历 bookings[]：bookings[i] = [i, j, k] 表示在 i 站增加 k 人即 counters[i - 1] += k
        // 在 j + 1 站减少 k 人即 counters[j] -= k
        // 遍历（整理）counter[] 数组，得到每站总人数：每站的人数为前一站人数加上当前人数变化 counters[i] += counters[i - 1]

        int[] counters = new int[n];

        for (int[] booking : bookings) {
            counters[booking[0] - 1] += booking[2];
            if (booking[1] < n)
                counters[booking[1]] -= booking[2];
        }

        for (int i = 1; i < n; ++i)
            counters[i] += counters[i - 1];

        return counters;
    }
}
