package com.bobking.leetcode.training;

import java.util.Collections;

/**
 * @author BobKing
 * @create 2023-02-12 11:01
 */
public class Number1138 {

    // 参考：https://leetcode.cn/problems/alphabet-board-path/solution/zhu-yi-z-de-wei-zhi-pythonjavacgo-by-end-1ha7/
    public String alphabetBoardPath(String target) {

        StringBuilder ans = new StringBuilder();
        int x = 0;
        int y = 0;

        for (char c : target.toCharArray()) {
            int nx = (c - 'a') / 5;
            int ny = (c - 'a') % 5;
            String v = "";
            // 竖直
            if (nx < x) {
                v = String.join("", Collections.nCopies(x - nx, "U"));
            } else {
                v = String.join("", Collections.nCopies(nx - x, "D"));
            }
            String h = "";
            // 水平
            if (ny < y) {
                h = String.join("", Collections.nCopies(y - ny, "L"));
            } else {
                h = String.join("", Collections.nCopies(ny - y, "R"));
            }
            ans.append(c != 'z' ? v + h : h + v).append('!');
            x = nx;
            y = ny;
        }
        return ans.toString();
    }
}
