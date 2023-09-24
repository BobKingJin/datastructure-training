package com.bobking.leetcode.training;

import java.util.*;

public class Number934 {

    private class Node {
        int r;
        int c;
        int depth;

        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            depth = d;
        }
    }

    // 参考：https://leetcode.cn/problems/shortest-bridge/solution/zui-duan-de-qiao-by-leetcode/
    public int shortestBridge(int[][] grid) {

        // 使用的方法非常直接：首先找到这两座岛，随后选择一座，将它不断向外延伸一圈，直到到达了另一座岛
        // 在寻找这两座岛时，使用深度优先搜索
        // 在向外延伸时，使用广度优先搜索

        // 通过对数组 grid 中的 1 进行深度优先搜索，可以得到两座岛的位置集合，分别为 source 和 target
        // 随后从 source 中的所有位置开始进行广度优先搜索，当它们到达了 target 中的任意一个位置时，搜索的层数就是答案

        int R = grid.length;
        int C = grid[0].length;
        int[][] colors = getComponents(grid);

        Queue<Node> queue = new LinkedList<Node>();
        Set<Integer> target = new HashSet<Integer>();

        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (colors[r][c] == 1) {
                    queue.add(new Node(r, c, 0));
                } else if (colors[r][c] == 2) {
                    target.add(r * C + c);
                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (target.contains(node.r * C + node.c))
                return node.depth - 1;
            for (int nei : neighbors(grid, node.r, node.c)) {
                int nr = nei / C;
                int nc = nei % C;
                if (colors[nr][nc] != 1) {
                    queue.add(new Node(nr, nc, node.depth + 1));
                    colors[nr][nc] = 1;
                }
            }
        }

        throw null;
    }

    private int[][] getComponents(int[][] A) {

        int R = A.length;
        int C = A[0].length;
        int[][] colors = new int[R][C];
        int t = 0;

        for (int r0 = 0; r0 < R; ++r0) {
            for (int c0 = 0; c0 < C; ++c0) {
                if (colors[r0][c0] == 0 && A[r0][c0] == 1) {
                    // Start dfs
                    Stack<Integer> stack = new Stack<Integer>();
                    stack.push(r0 * C + c0);
                    colors[r0][c0] = ++t;

                    while (!stack.isEmpty()) {
                        int node = stack.pop();
                        int r = node / C;
                        int c = node % C;
                        for (int nei : neighbors(A, r, c)) {
                            int nr = nei / C;
                            int nc = nei % C;
                            if (A[nr][nc] == 1 && colors[nr][nc] == 0) {
                                colors[nr][nc] = t;
                                stack.push(nr * C + nc);
                            }
                        }
                    }
                }
            }
        }

        return colors;
    }

    private List<Integer> neighbors(int[][] A, int r, int c) {

        int R = A.length;
        int C = A[0].length;
        List<Integer> ans = new ArrayList<Integer>();

        if (0 <= r - 1)
            ans.add((r - 1) * R + c);
        if (0 <= c - 1)
            ans.add(r * R + (c - 1));
        if (r + 1 < R)
            ans.add((r + 1) * R + c);
        if (c + 1 < C)
            ans.add(r * R + (c + 1));

        return ans;
    }
}
