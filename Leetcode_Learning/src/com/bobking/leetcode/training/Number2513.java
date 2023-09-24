package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-05-20 17:12
 */
public class Number2513 {

    // 参考：https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays/solution/er-fen-da-an-by-endlesscheng-y8fp/
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {

        // 求最小公倍数
        long gong = lcm(divisor1, divisor2);
        long left = 1;
        long right = ((long) uniqueCnt2 + uniqueCnt1) * 2;
        int res = -1;

        while (left <= right) {
            long mid = (left + right) / 2;
            if (check(mid, gong, divisor1, divisor2, uniqueCnt1, uniqueCnt2)) {
                res = (int) mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean check(long x, long gong, int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long left1 = Math.max(uniqueCnt1 - x / divisor2 + x / gong, 0L);
        long left2 = Math.max(uniqueCnt2 - x / divisor1 + x / gong, 0L);
        long common = x - x / divisor1 - x / divisor2 + x / gong;
        return common >= (left1 + left2);
    }

    public int gcd(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        a = max;
        b = min;
        while (b > 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public long lcm(int a, int b) {
        return (1L * a * b / gcd(a, b));
    }
}
