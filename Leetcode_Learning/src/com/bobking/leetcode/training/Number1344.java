package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-02-25 11:18
 */
public class Number1344 {

    // 参考：https://leetcode.cn/problems/angle-between-hands-of-a-clock/solution/shi-zhong-zhi-zhen-de-jia-jiao-by-leetcode/
    public double angleClock(int hour, int minutes) {

        int oneMinAngle = 6;
        int oneHourAngle = 30;

        double minutesAngle = oneMinAngle * minutes;
        double hourAngle = (hour % 12 + minutes / 60.0) * oneHourAngle;

        double diff = Math.abs(hourAngle - minutesAngle);
        return Math.min(diff, 360 - diff);
    }
}
