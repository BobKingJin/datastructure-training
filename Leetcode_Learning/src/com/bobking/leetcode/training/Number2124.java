package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-09 9:13
 */
public class Number2124 {

    // 参考：https://leetcode.cn/problems/check-if-all-as-appears-before-all-bs/solution/jian-cha-shi-fou-suo-you-a-du-zai-b-zhi-e7p0q/
    public boolean checkString1(String s) {
        return !s.contains("ba");
    }

    // 参考：https://leetcode.cn/problems/check-if-all-as-appears-before-all-bs/solution/jian-cha-shi-fou-suo-you-a-du-zai-b-zhi-e7p0q/
    public boolean checkString2(String s) {

        int n = s.length();
        int lasta = -1;
        int firstb = n;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                lasta = Math.max(lasta, i);
            } else {
                firstb = Math.min(firstb, i);
            }
        }
        return lasta < firstb;
    }
}
