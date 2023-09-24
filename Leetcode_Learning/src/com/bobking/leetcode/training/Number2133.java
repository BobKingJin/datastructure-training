package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-09-25 16:52
 */
public class Number2133 {

    // 参考：https://leetcode.cn/problems/check-if-every-row-and-column-contains-all-numbers/solution/jian-cha-shi-fou-mei-yi-xing-mei-yi-lie-uwrwu/
    public boolean checkValid(int[][] matrix) {

        int n = matrix.length;
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 行
                set1.add(matrix[i][j]);
                // 列
                set2.add(matrix[j][i]);
            }

            if (set1.size() != n || set2.size() != n)
                return false;

            set1.clear();
            set2.clear();
        }

        return true;
    }
}
