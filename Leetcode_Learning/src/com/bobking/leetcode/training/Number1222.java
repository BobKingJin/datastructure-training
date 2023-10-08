package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-10-07 8:05
 */
public class Number1222 {

    private final int[][] directions = {{1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {

        boolean[][] isQueen = new boolean[8][8];

        for (int[] q : queens)
            isQueen[q[0]][q[1]] = true;

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (int[] d : directions) {
            int x = king[0] + d[0];
            int y = king[1] + d[1];
            while (0 <= x && x < 8 && 0 <= y && y < 8) {
                if (isQueen[x][y]) {
                    ans.add(List.of(x, y));
                    break;
                }
                x += d[0];
                y += d[1];
            }
        }
        return ans;
    }
}
