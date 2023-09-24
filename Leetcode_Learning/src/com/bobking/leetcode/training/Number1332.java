package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-11-17 23:25
 */
public class Number1332 {

    // 参考：https://leetcode.cn/problems/remove-palindromic-subsequences/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-0zwn/
    public int removePalindromeSub(String s) {

        int n = s.length();
        int i = 0;
        int j = n - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return 2;
            i++;
            j--;
        }
        return 1;
    }
}
