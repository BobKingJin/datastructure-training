package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-30 10:33
 */
public class Number1750 {

    // 参考：https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/solution/shan-chu-zi-fu-chuan-liang-duan-xiang-to-biep/
    public int minimumLength(String s) {

        int n = s.length();
        int left = 0;
        int right = n - 1;

        while (left < right && s.charAt(left) == s.charAt(right)) {
            char c = s.charAt(left);
            while (left <= right && s.charAt(left) == c)
                left++;

            while (left <= right && s.charAt(right) == c)
                right--;
        }
        return right - left + 1;
    }
}
