package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Date: 2024/3/1 22:39
 * @Author: BobKing
 * @Description:
 */
public class Number1914 {

    public int[][] rotateGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;
        int layerNumber = Math.min(m, n) / 2;

        for (int i = 0; i < layerNumber; ++i) {
            // 计算出来当前层需要多大的数组来存放, 计算方法是当前层最大尺寸 - 内部下一层尺寸
            int[] data = new int[(m - 2 * i) * (n - 2 * i) - (m - (i + 1) * 2) * (n - (i + 1) * 2)];
            int idx = 0;
            // 从左往右
            for (int offset = i; offset < n - i - 1; ++offset)
                data[idx++] = grid[i][offset];
            // 从上往下
            for (int offset = i; offset < m - i - 1; ++offset)
                data[idx++] = grid[offset][n - i - 1];
            // 从右往左
            for (int offset = n - i - 1; offset > i; --offset)
                data[idx++] = grid[m - i - 1][offset];
            // 从下往上
            for (int offset = m - i - 1; offset > i; --offset)
                data[idx++] = grid[offset][i];
            // 把旋转完的放回去
            Integer[] ret = rotateVector(data, k);
            idx = 0;
            // 从左往右
            for (int offset = i; offset < n - i - 1; ++offset)
                grid[i][offset] = ret[idx++];
            // 从上往下
            for (int offset = i; offset < m - i - 1; ++offset)
                grid[offset][n - i - 1] = ret[idx++];
            // 从右往左
            for (int offset = n - i - 1; offset > i; --offset)
                grid[m - i - 1][offset] = ret[idx++];
            // 从下往上
            for (int offset = m - i - 1; offset > i; --offset)
                grid[offset][i] = ret[idx++];
        }
        return grid;
    }

    private Integer[] rotateVector(int[] vector, int offset) {
        // 取余
        offset = offset % vector.length;
        Deque<Integer> deque = new ArrayDeque<Integer>();
        for (int item : vector)
            deque.add(item);
        // 旋转操作
        while (offset-- > 0)
            deque.addLast(deque.pollFirst());
        return deque.toArray(new Integer[0]);
    }


}
