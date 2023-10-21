package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-14 9:21
 */
public class Number427 {

    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    int[][] g;

    public Node construct1(int[][] grid) {
        this.g = grid;
        // 判断该矩阵是否为全 0 或全 1
        // 如果是, 则直接创建根节点（该节点四个子节点属性均为空）并进行返回
        // 如果不是则创建根节点, 递归创建四个子节点并进行赋值, 利用左上角 (a, b) 和右下角 (c, d) 可算的横纵坐标的长度为 c - a + 1 和 d - b + 1
        // 从而计算出将当前矩阵四等分所得到的子矩阵的左上角和右下角坐标
        return dfs1(0, 0, g.length - 1, g.length - 1);
    }

    // 返回以 (a, b) 为左上角，(c, d) 为右下角所代表的矩阵的根节点
    private Node dfs1(int a, int b, int c, int d) {

        boolean ok = true;
        int t = g[a][b];
        for (int i = a; i <= c && ok; i++) {
            for (int j = b; j <= d && ok; j++) {
                if (g[i][j] != t)
                    ok = false;
            }
        }
        if (ok)
            return new Node(t == 1, true);

        Node root = new Node(t == 1, false);
        int dx = c - a + 1;
        int dy = d - b + 1;
        root.topLeft = dfs1(a, b, a + dx / 2 - 1, b + dy / 2 - 1);
        root.topRight = dfs1(a, b + dy / 2, a + dx / 2 - 1, d);
        root.bottomLeft = dfs1(a + dx / 2, b, c, b + dy / 2 - 1);
        root.bottomRight = dfs1(a + dx / 2, b + dy / 2, c, d);
        return root;
    }

    int[][] sum = new int[70][70];

    public Node construct2(int[][] grid) {

        g = grid;
        int n = grid.length;
        // 使用前缀和优化「判断全 0 和全 1」的操作：对矩阵 grid 求前缀和数组 sum，对于一个以左上角为 (a, b)，右下角为 (c, d)的子矩阵而言
        // 其所包含的格子总数为 tot = (c - a + 1) * (d - b + 1)个，当且仅当矩阵和为 0 或 tot 时，矩阵全 0 或 1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + g[i - 1][j - 1];
        }
        return dfs2(0, 0, n - 1, n - 1);
    }

    private Node dfs2(int a, int b, int c, int d) {

        int cur = sum[c + 1][d + 1] - sum[a][d + 1] - sum[c + 1][b] + sum[a][b];
        int dx = c - a + 1;
        int dy = d - b + 1;
        int tot = dx * dy;

        if (cur == 0 || cur == tot)
            return new Node(g[a][b] == 1, true);

        Node root = new Node(g[a][b] == 1, false);
        root.topLeft = dfs2(a, b, a + dx / 2 - 1, b + dy / 2 - 1);
        root.topRight = dfs2(a, b + dy / 2, a + dx / 2 - 1, d);
        root.bottomLeft = dfs2(a + dx / 2, b, c, b + dy / 2 - 1);
        root.bottomRight = dfs2(a + dx / 2, b + dy / 2, c, d);
        return root;
    }
}
