package com.bobking.leetcode.training;

public class Number1154 {

    private int[] nums = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int[] f = new int[13];

    private void init() {
        for (int i = 1; i <= 12; i++)
            f[i] = f[i - 1] + nums[i - 1];
    }

    // 参考：https://leetcode.cn/problems/day-of-the-year/solution/gong-shui-san-xie-jian-dan-qian-zhui-he-lwo2g/
    public int dayOfYear(String date) {

        init();

        String[] ss = date.split("-");
        int y = Integer.parseInt(ss[0]);
        int m = Integer.parseInt(ss[1]);
        int d = Integer.parseInt(ss[2]);
        // 当年份为闰年且当前月份超过了 2 月份，要追加一天
        boolean isLeap = (y % 4 == 0 && y % 100 != 0) || y % 400 == 0;
        int ans = m > 2 && isLeap ? f[m - 1] + 1 : f[m - 1];
        return ans + d;
    }
}
