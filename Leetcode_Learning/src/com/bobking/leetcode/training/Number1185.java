package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-30 15:26
 */
public class Number1185 {

    String[] ss = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 参考：https://leetcode.cn/problems/day-of-the-week/solution/gong-shui-san-xie-jian-dan-ri-qi-tong-ji-czt6/
    public String dayOfTheWeek(int day, int month, int year) {

        int ans = 4;

        for (int i = 1971; i < year; i++) {
            boolean isLeap = (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
            ans += isLeap ? 366 : 365;
        }

        for (int i = 1; i < month; i++) {
            ans += nums[i - 1];
            if (i == 2 && ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0))
                ans++;
        }

        ans += day;

        return ss[ans % 7];
    }
}
