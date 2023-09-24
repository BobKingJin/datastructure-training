package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-03 22:57
 */
public class Number2165 {

    public long smallestNumber(long num) {

        // 利用 int[] 数组保存每个数字出现的次数
        // 如果是正数就先找到第一个非0最小数放到首位，然后按数字从小到大依次加到后面；
        // 如果是负数就按数字从大到小依次添加最后取反。

        long temp = num;
        int[] cnt = new int[10];
        num = Math.abs(num);
        while (num != 0) {
            int n = (int) (num % 10);
            cnt[n]++;
            num /= 10;
        }
        long ans = 0;
        if (temp > 0) {
            for (int i = 1; i < 10; i++) {
                if (cnt[i] != 0) {
                    ans = i;
                    cnt[i]--;
                    break;
                }
            }
            for (int i = 0; i < 10; i++) {
                while (cnt[i] > 0) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                }
            }
        } else {
            for (int i = 9; i >= 0; i--) {
                while (cnt[i] > 0) {
                    ans = ans * 10 + i;
                    cnt[i]--;
                }
            }
            ans *= -1;
        }
        return ans;
    }
}
