package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

public class Number733 {

    // 并查集解法参考：https://leetcode-cn.com/problems/flood-fill/solution/flood-fill-suan-fa-mo-xing-xiang-jie-dfsbfsbing-ch/

    // dfs
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor) {

        if (image == null || image[0] == null || image.length == 0 || image[0].length == 0)
            return image;

        // 如果颜色相同则不处理
        if (image[sr][sc] != newColor)
            dfs(image, sr, sc, newColor, image[sr][sc]);

        return image;
    }

    private void dfs(int[][] image, int x, int y, int newColor, int originColor) {

        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length)
            return;

        if (image[x][y] == originColor) {
            // 将颜色替换
            image[x][y] = newColor;
            // 深度优先搜索四周的像素点
            dfs(image, x - 1, y, newColor, originColor);
            dfs(image, x + 1, y, newColor, originColor);
            dfs(image, x, y - 1, newColor, originColor);
            dfs(image, x, y + 1, newColor, originColor);
        }

        return;
    }

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};

    // bfs
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {

        if (image == null || image[0] == null || image.length == 0 || image[0].length == 0 || image[sr][sc] == newColor)
            return image;

        int currColor = image[sr][sc];
        if (image[sr][sc] == newColor)
            return image;

        int n = image.length;
        int m = image[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;
        while (!queue.isEmpty()) {

            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];
            for (int i = 0; i < 4; i++) {
                int mx = x + dx[i];
                int my = y + dy[i];
                if (mx >= 0 && mx < n && my >= 0 && my < m && image[mx][my] == currColor) {
                    queue.offer(new int[]{mx, my});
                    image[mx][my] = newColor;
                }
            }
        }
        return image;
    }


}
