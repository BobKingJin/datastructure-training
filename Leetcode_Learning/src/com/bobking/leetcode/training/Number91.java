package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2021-04-04 15:54
 */
public class Number91 {

    // 参考：程序猿代码指南P239
    // 参考：https://leetcode-cn.com/problems/decode-ways/solution/c-wo-ren-wei-hen-jian-dan-zhi-guan-de-jie-fa-by-pr/
    public int numDecodings1(String s) {

        if (s == null || s.length() == 0)
            return 0;

        if (s.charAt(0) == '0')
            return 0;

        int pre = 1;
        int cur = 1;

        // 从前往后
        for (int i = 1; i < s.length(); i++) {

            int temp = cur;
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    cur = pre;
                } else {
                    return 0;
                }
            } else if (s.charAt(i - 1) == '1') {
                cur = cur + pre;
            } else if (s.charAt(i - 1) == '2' && (s.charAt(i) >= '0' && s.charAt(i) <= '6')) {
                cur = cur + pre;
            }

            pre = temp;
        }

        return cur;
    }

    // 参考：程序猿代码指南P238
    // 参考：https://leetcode-cn.com/problems/decode-ways/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-3/
    public int numDecodings2(String s) {

        if (s == null || s.length() == 0)
            return 0;
        // 以 0 作为开始
        if (s.charAt(0) == '0')
            return 0;

        return recursion1(s, 0);
    }

    private int recursion1(String s, int index) {

        // 即这种切割符合要求，返回 1
        if (index == s.length())
            return 1;

        // 即 index - (s.length - 1) 到这个部分的结果为 0
        if (s.charAt(index) == '0')
            return 0;
        // 如果 s.charAt(index) != '0'，那么一定是 1 ~ 9 中的一个
        int part1 = recursion1(s, index + 1);
        int part2 = 0;

        if (index < s.length() - 1) {
            int ten = s.charAt(index) - '0';
            int one = s.charAt(index + 1) - '0';
            if ((ten * 10 + one) <= 26)
                part2 = recursion1(s, index + 2);
        }

        return part1 + part2;
    }

    public int numDecodings3(String s) {

        if (s == null || s.length() == 0)
            return 0;
        if (s.charAt(0) == '0')
            return 0;

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        return recursion2(s, 0, map);
    }

    private int recursion2(String s, int index, Map<Integer, Integer> map) {

        if (index == s.length())
            return 1;

        if (s.charAt(index) == '0')
            return 0;

        int solved = map.getOrDefault(index, 0);
        if (solved != 0)
            return solved;

        int part1 = recursion2(s, index + 1, map);
        int part2 = 0;

        if (index < s.length() - 1) {
            int ten = s.charAt(index) - '0';
            int one = s.charAt(index + 1) - '0';
            if ((ten * 10 + one) <= 26) {
                part2 = recursion2(s, index + 2, map);
            }
        }

        map.put(index, part1 + part2);
        return part1 + part2;
    }
}
