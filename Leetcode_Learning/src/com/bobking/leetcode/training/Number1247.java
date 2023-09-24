package com.bobking.leetcode.training;

public class Number1247 {

    // 参考：https://leetcode.cn/problems/minimum-swaps-to-make-strings-equal/solution/java-tan-xin-suan-fa-xiang-jie-zhi-xing-yong-shi-n/
    public int minimumSwap(String s1, String s2) {

        int len = s1.length();
        int xy = 0;
        int yx = 0;

        for(int i = 0; i < len; i ++) {

            if(s1.charAt(i) == s2.charAt(i)) {
                continue;
            } else if(s1.charAt(i) == 'x') {
                xy++;
            } else {
                yx++;
            }
        }

        return ((xy + yx) & 1) == 1 ? -1 : (xy + 1) / 2 + (yx + 1) / 2;
    }
}
