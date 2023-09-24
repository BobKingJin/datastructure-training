package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-12 10:34
 */
public class Number1781 {

    public int beautySum(String s) {

        int length = s.length();
        int sum = 0;

        // 这里 length 可以从 3 开始，length等于 2 的话，max - min肯定等于 0，两个字母只有可能是相同，max = min = 2
        // 两个字母不同，则 max = min = 1，所以 length = 2这一轮查找可以跳过
        for (int k = 3; k <= length; k++) {
            int l = 0;
            int r = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int[] count = new int[26];
            while (r < length) {
                count[s.charAt(r) - 'a']++;
                r++;
                // 产生一个窗口
                while (l + k == r) {
                    min = Integer.MAX_VALUE;
                    max = Integer.MIN_VALUE;
                    for (int i = 0; i < 26; i++) {
                        if (count[i] > 0) {
                            min = Math.min(min, count[i]);
                            max = Math.max(max, count[i]);
                        }
                    }
                    sum += max - min;
                    count[s.charAt(l) - 'a']--;
                    l++;
                }
            }
        }
        return sum;
    }
}
