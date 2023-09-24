package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number498 {

    // 参考：程序猿代码指南P365
    public int[] findDiagonalOrder(int[][] mat) {

        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0)
            return new int[0];

        int tR = 0;
        int tC = 0;
        int dR = 0;
        int dC = 0;
        int endR = mat.length - 1;
        int endC = mat[0].length - 1;
        // 从上往下 例如：1 2 3 4
        //              5 6 7 8
        //              9 10 11 12
        // 从上往下即 2 -> 5
        boolean fromUp = false;
        List<Integer> list = new ArrayList<Integer>();

        while (tR != endR + 1) {
            printLevel(mat, list, tR, tC, dR, dC, fromUp);
            tR = (tC == endC) ? tR + 1 : tR;
            tC = (tC == endC) ? tC : tC + 1;
            dC = (dR == endR) ? dC + 1 : dC;
            dR = (dR == endR) ? dR : dR + 1;
            fromUp = !fromUp;
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }

    private void printLevel(int[][] mat, List<Integer> list, int tR, int tC, int dR, int dC, boolean fromUp) {

        if (fromUp) {
            while (tR != dR + 1)
                list.add(mat[tR++][tC--]);
        } else {
            while (dR != tR - 1)
                list.add(mat[dR--][dC++]);
        }
    }

}
