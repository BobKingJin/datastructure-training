package com.bobking.leetcode.training;

/**
 * @Date: 2024/2/25 19:21
 * @Author: BobKing
 * @Description:
 */
public class Number1888 {

    // 参考: https://leetcode.cn/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/solutions/815295/minimum-number-of-flips-by-ikaruga-lu32/
    public int minFlips(String s) {

        int len = s.length();
        String target = "01";
        int cnt = 0;
        for (int i = 0; i < len; i++)
            cnt += s.charAt(i) != target.charAt(i % 2) ? 1 : 0;

        // cnt 是 01010 模式的监测次数
        // len - cnt是 10101 模式的监测次数
        int ans = Math.min(cnt, len - cnt);

        if (s.length() % 2 == 0)
            // 为偶数的情况 无论是匹配 01, 10, 怎么拼接字符串, 两种都需要执行一样的操作二数量
            return ans;

        s += s;
        for (int i = 0; i < len; i++) {
            // 从 01010 -> 10101 的模式匹配, 只用关心首尾
            // 例如: 11010 -> 10101
            if (s.charAt(i) != target.charAt(i % 2))
                // 说明在上一个(01010)模式下 s的字符串头不等于0
                // 则在新模式(10101)中不用对该字符位置进行操作二, 所以 cnt - 1
                cnt -= 1;
            if (s.charAt(i + len) != target.charAt((i + len) % 2))
                // 说明新加进来的尾字符, 是和新模式(10101)尾字符不相匹配的
                // 则在新模式(10101)中需要对该字符位置进行操作二, 所以 cnt + 1
                cnt += 1;
            ans = Math.min(ans, Math.min(cnt, len - cnt));
        }
        return ans;
    }
}
