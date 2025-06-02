package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/6/2 10:41
 * @Author: BobKing
 * @Description:
 */
public class Number838 {

    // 参考: https://leetcode.cn/problems/push-dominoes/solutions/3667176/fen-lei-tao-lun-jian-ji-xie-fa-pythonjav-bztd/?envType=daily-question&envId=2025-06-02
    public String pushDominoes(String dominoes) {
        // 前后各加一个哨兵
        char[] s = ("L" + dominoes + "R").toCharArray();
        // 上一个 L 或 R 的位置
        int pre = 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i] == '.') {
                continue;
            }
            // L...L 或 R...R
            if (s[i] == s[pre]) {
                // 全变成 s[i]
                Arrays.fill(s, pre + 1, i, s[i]);
            } else if (s[i] == 'L') { // R...L
                // 前一半变 R
                Arrays.fill(s, pre + 1, (pre + i + 1) / 2, 'R');
                // 后一半变 L
                Arrays.fill(s, (pre + i) / 2 + 1, i, 'L');
            }
            pre = i;
        }
        return new String(s, 1, s.length - 2);
    }

}
