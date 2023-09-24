package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-04-23 8:08
 */
public class Number1105 {

    private int[][] books;
    private int shelfWidth;

    // 参考：https://leetcode.cn/problems/filling-bookcase-shelves/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-0vg6/
    public int minHeightShelves1(int[][] books, int shelfWidth) {
        this.books = books;
        this.shelfWidth = shelfWidth;
        // dfs(i) 表示把 books[0] 到 books[i] 按顺序摆放后的最小高度
        return dfs1(books.length - 1);
    }

    private int dfs1(int i) {
        if (i < 0)
            return 0;
        int res = Integer.MAX_VALUE;
        int maxH = 0;
        int leftW = shelfWidth;
        // 枚举最后一层的第一本书的下标 j
        for (int j = i; j >= 0; --j) {
            leftW -= books[j][0];
            if (leftW < 0)
                break;
            maxH = Math.max(maxH, books[j][1]);
            res = Math.min(res, dfs1(j - 1) + maxH);
        }
        return res;
    }

    private int[] memo;

    // 参考：https://leetcode.cn/problems/filling-bookcase-shelves/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-0vg6/
    public int minHeightShelves2(int[][] books, int shelfWidth) {
        this.books = books;
        this.shelfWidth = shelfWidth;
        int n = books.length;
        memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs2(n - 1);
    }

    private int dfs2(int i) {
        if (i < 0)
            return 0;
        if (memo[i] != -1)
            return memo[i];
        int res = Integer.MAX_VALUE;
        int maxH = 0;
        int leftW = shelfWidth;
        for (int j = i; j >= 0; --j) {
            leftW -= books[j][0];
            if (leftW < 0)
                break;
            maxH = Math.max(maxH, books[j][1]);
            res = Math.min(res, dfs2(j - 1) + maxH);
        }
        return memo[i] = res;
    }

    // 参考：https://leetcode.cn/problems/filling-bookcase-shelves/solution/jiao-ni-yi-bu-bu-si-kao-dong-tai-gui-hua-0vg6/
    public int minHeightShelves3(int[][] books, int shelfWidth) {

        int n = books.length;
        // f[0] = 0，翻译自 dfs(-1) = 0
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            f[i + 1] = Integer.MAX_VALUE;
            int maxH = 0;
            int leftW = shelfWidth;
            for (int j = i; j >= 0; --j) {
                leftW -= books[j][0];
                if (leftW < 0)
                    break;
                maxH = Math.max(maxH, books[j][1]);
                f[i + 1] = Math.min(f[i + 1], f[j] + maxH);
            }
        }
        // 翻译自 dfs(n - 1)
        return f[n];
    }

}
