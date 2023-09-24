package com.bobking.leetcode.training;

import java.time.Clock;

/**
 * @author BobKing
 * @create 2022-08-21 10:42
 */
public class Number686 {

    // 参考：https://leetcode.cn/problems/repeated-string-match/solution/gong-shui-san-xie-yi-ti-san-jie-qia-chan-3hbr/
    public int repeatedStringMatch1(String a, String b) {

        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0)
            sb.append(a);

        Clock clock = Clock.systemDefaultZone();
        long start = clock.millis();

        while (clock.millis() - start < 100) {
            if (sb.indexOf(b) != -1)
                return ans;
            sb.append(a);
            ans++;
        }

        return -1;
    }

    // 参考：https://leetcode.cn/problems/repeated-string-match/solution/gong-shui-san-xie-yi-ti-san-jie-qia-chan-3hbr/
    public int repeatedStringMatch2(String a, String b) {

        StringBuilder sb = new StringBuilder();
        int ans = 0;
        while (sb.length() < b.length() && ++ans > 0)
            sb.append(a);

        sb.append(a);
        int idx = sb.indexOf(b);

        if (idx == -1)
            return -1;

        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }
}
