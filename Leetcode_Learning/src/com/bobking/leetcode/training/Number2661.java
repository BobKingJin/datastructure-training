package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @author BobKing
 * @create 2023-06-23 21:25
 */
public class Number2661 {

    public int firstCompleteIndex(int[] arr, int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;
        // 分别记录行和列有多少个元素被图
        int[] row = new int[m];
        int[] col = new int[n];
        int len = arr.length;

        HashMap<Integer, int[]> hashMap = new HashMap<Integer, int[]>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                hashMap.put(mat[i][j], new int[]{i, j});
        }

        for (int i = 0; i < len; i++) {
            int data = arr[i];
            if (hashMap.containsKey(data)) {
                int x = hashMap.get(data)[0];
                int y = hashMap.get(data)[1];
                row[x]++;
                col[y]++;
                if (row[x] == n || col[y] == m)
                    return i;
            }
        }

        return -1;
    }
}
