package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-06-27 7:05
 */
public class Number2249 {

    public int countLatticePoints(int[][] circles) {

        // 坐标总的范围在 [0, 200] 之间因此可用一个矩阵来存储是否出现过
        boolean[][] inCir = new boolean[201][201];
        // 遍历每个圆的圆心半径范围内的所有点
        for (int[] circle : circles) {
            int l = circle[0] - circle[2];
            int r = circle[0] + circle[2];
            for (int x = l; x <= r; x++) {
                int d = circle[1] - circle[2];
                int u = circle[1] + circle[2];
                for (int y = d; y <= u; y++) {
                    if (inCircle(x, y, circle))
                        inCir[x][y] = true;
                }
            }
        }
        int res = 0;
        for (boolean[] node : inCir) {
            for (boolean bool : node)
                res += bool ? 1 : 0;
        }
        return res;
    }

    // 判断是否在圆内
    private boolean inCircle(int x, int y, int[] circle) {
        return (circle[0] - x) * (circle[0] - x) + (circle[1] - y) * (circle[1] - y) <= circle[2] * circle[2];
    }
}
