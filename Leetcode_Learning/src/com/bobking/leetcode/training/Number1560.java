package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-07-02 8:19
 */
public class Number1560 {

    // 参考：https://leetcode.cn/problems/most-visited-sector-in-a-circular-track/solution/yuan-xing-sai-dao-shang-jing-guo-ci-shu-zui-duo--2/
    public List<Integer> mostVisited(int n, int[] rounds) {

        List<Integer> res = new ArrayList<Integer>();
        int length = rounds.length;
        int start = rounds[0];
        int end = rounds[length - 1];

        if (start <= end) {
            for (int i = start; i <= end; i++)
                res.add(i);
        } else {
            // 由于题目要求按扇区大小排序，因此要将区间分成两部分
            for (int i = 1; i <= end; i++)
                res.add(i);
            for (int i = start; i <= n; i++)
                res.add(i);
        }

        return res;
    }
}
