package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-10-28 10:14
 */
public class Number1684 {

    // 参考：https://leetcode.cn/problems/count-the-number-of-consistent-strings/solution/zhuang-tai-ya-suo-wei-yun-suan-by-zheng-xl00a/
    public int countConsistentStrings(String allowed, String[] words) {

        int ans = solve(allowed);
        int total = 0;

        for (String word : words) {
            int res = solve(word);
            if((res & ans) == res)
                total++;
        }

        return total;
    }

    private int solve(String s) {

        int ans = 0;
        for(int i = 0; i < s.length(); i++) {
            int x = s.charAt(i) - 'a';
            ans |= (1 << x);
        }
        return ans;
    }
}
