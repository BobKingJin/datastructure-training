package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number51 {

    List<List<String>> res = new ArrayList<List<String>>();

    // 参考：https://leetcode-cn.com/problems/n-queens/solution/hui-su-suan-fa-xiang-jie-by-labuladong/
    public List<List<String>> solveNQueens(int n) {

        if (n < 1)
            return res;

        List<StringBuilder> track = new ArrayList<StringBuilder>();

        for (int i = 0; i < n; i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < n; j++)
                str.append(".");
            track.add(str);
        }

        brackTrace(track, 0);

        return res;
    }

    // 按行递归
    private void brackTrace(List<StringBuilder> track, int row) {

        if (row == track.size()) {
            List<String> list = new ArrayList<String>();
            for (StringBuilder sb : track)
                list.add(sb.toString());

            res.add(list);
            return;
        }

        // 即把当前行的每一列都尝试一遍
        for (int col = 0; col < track.get(row).length(); col++) {

            if (!isValid(track, row, col))
                continue;

            track.get(row).setCharAt(col, 'Q');
            brackTrace(track, row + 1);
            // 回溯
            track.get(row).setCharAt(col, '.');
        }
    }

    private boolean isValid(List<StringBuilder> track, int row, int col) {

        // 不可能会有行冲突，因为是按行递归的
        // 检查列是否有冲突
        for (int i = 0; i < track.size(); i++) {
            if (track.get(i).charAt(col) == 'Q')
                return false;
        }

        // 检查右上方是否有冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < track.size(); i--, j++) {
            if (track.get(i).charAt(j) == 'Q')
                return false;
        }

        // 检查左上方是否有冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (track.get(i).charAt(j) == 'Q')
                return false;
        }

        return true;
    }
}
