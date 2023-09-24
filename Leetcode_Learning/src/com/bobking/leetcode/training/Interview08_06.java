package com.bobking.leetcode.training;

import java.util.List;

/**
 * @author BobKing
 * @create 2021-06-13 7:49
 */
public class Interview08_06 {

    // 参考：程序猿代码指南P217
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {

        if (A == null || A.size() == 0)
            return;

        recusion(A.size(), A, B, C);
    }

    private void recusion(int n, List<Integer> A, List<Integer> B, List<Integer> C) {

        if (n == 1) {
            C.add(A.remove(A.size() - 1));
            return;
        } else {
            // 把 1 - (n - 1) 从左边移动到中间
            recusion(n - 1, A, C, B);
            // 把 n 从左边移动到右边
            C.add(A.remove(A.size() - 1));
            // 把 1 - (n - 1) 从中间移动到右边
            recusion(n - 1, B, A, C);
        }
    }
}
