package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-07-16 10:02
 */
public class Number1519 {

    private int[] res;
    private boolean[] visited;

    // 参考：https://leetcode.cn/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/solution/leetcode-zi-shu-zhong-biao-qian-xiang-tong-de-jie-/
    public int[] countSubTrees(int n, int[][] edges, String labels) {

        List<Integer>[] points = new List[n];
        for(int i = 0; i < n; i++)
            points[i] = new ArrayList<Integer>();

        for(int[] p : edges){
            points[p[0]].add(p[1]);
            points[p[1]].add(p[0]);
        }

        int[] ls = new int[n];
        for(int i = 0; i < n; i++)
            ls[i] = labels.charAt(i) - 'a';

        res = new int[n];
        visited = new boolean[n];
        visited[0] = true;
        dfs(0, points, ls);
        return res;
    }

    private int[] dfs(int i, List<Integer>[] points, int[] ls){

        int[] curLs = new int[26];
        // 添加自身节点
        curLs[ls[i]]++;
        for(int child : points[i]){
            // 判断是否已经遍历过该节点，如果遍历过，那么跳过
            // 因为这是无向图, 1 可以到 2，2 也可以到 1，因此，当 1 到 2 的时候，需要记录 1 已经访问，这样，从 2 出发，就不会再到 1 了
            if(visited[child])
                continue;

            visited[child] = true;
            int[] childLs = dfs(child, points, ls);
            for(int k = 0; k < 26; k++)
                curLs[k] += childLs[k];
        }
        res[i] = curLs[ls[i]];
        return curLs;
    }
}
