package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;

public class Number827 {

    // 参考：https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
    // 参考：https://leetcode-cn.com/problems/making-a-large-island/solution/dao-yu-wen-ti-mei-you-na-yao-nan-du-li-x-cgbv/
    public int largestIsland(int[][] grid) {

        // 先计算出所有岛屿的面积，在所有的格子上标记出岛屿的面积。然后搜索哪个海洋格子相邻的两个岛屿面积最大
        if (grid == null || grid.length == 0)
            return 1;

        int res = 0;
        // 0是海洋  1是陆地，为了避免冲突，从 2 开始
        // index 相当于岛屿编号
        int index = 2;
        // 序号对应面积的一个映射，因为面积要最后结束才能计算出来，所以遍历过程中用 index 代替，放在映射中
        HashMap<Integer, Integer> indexAndAreas = new HashMap<Integer, Integer>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    int area = area(grid, r, c, index);
                    indexAndAreas.put(index, area);
                    index++;
                    res = Math.max(res, area);
                }
            }
        }
        // 如果没有陆地，那么就造 1 块
        if (res == 0)
            return 1;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                // 遍历海洋格子
                if (grid[r][c] == 0) {
                    // 把上下左右四邻放入set里，set的目的是去重
                    HashSet<Integer> hashSet = findNeighbour(grid, r, c);
                    // 周围没有陆地就不必再继续，所以continue
                    if (hashSet.size() < 1)
                        continue;
                    // 起始是 1，直接把造出来的 1 计算进去
                    int twoIsland = 1;
                    // 通过序号找到面积
                    for (Integer i : hashSet)
                        twoIsland += indexAndAreas.get(i);
                    res = Math.max(res, twoIsland);
                }
            }
        }
        return res;
    }

    private HashSet<Integer> findNeighbour(int[][] grid, int r, int c) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        if (inArea(grid, r - 1, c) && grid[r - 1][c] != 0)
            hashSet.add(grid[r - 1][c]);
        if (inArea(grid, r + 1, c) && grid[r + 1][c] != 0)
            hashSet.add(grid[r + 1][c]);
        if (inArea(grid, r, c - 1) && grid[r][c - 1] != 0)
            hashSet.add(grid[r][c - 1]);
        if (inArea(grid, r, c + 1) && grid[r][c + 1] != 0)
            hashSet.add(grid[r][c + 1]);
        return hashSet;
    }

    private int area(int[][] grid, int r, int c, int index) {

        if (!inArea(grid, r, c))
            return 0;

        if (grid[r][c] != 1)
            return 0;

        grid[r][c] = index;
        return 1 + area(grid, r - 1, c, index)
                 + area(grid, r + 1, c, index)
                 + area(grid, r, c - 1, index)
                 + area(grid, r, c + 1, index);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

}
