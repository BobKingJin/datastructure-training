package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-11-10 11:52
 */
public class Number834 {

    // 邻接表
    private List<List<Integer>> graph = new ArrayList<List<Integer>>();
    // 距离和
    int[] distSum;
    // 子树节点个数（包括自己）
    int[] nodeNum;

    // 参考：https://leetcode.cn/problems/sum-of-distances-in-tree/solution/shou-hua-tu-jie-shu-zhong-ju-chi-zhi-he-shu-xing-d/
    public int[] sumOfDistancesInTree(int n, int[][] edges) {

        for(int i = 0; i < n; i++)
            graph.add(new ArrayList<Integer>());

        for(int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dst = edges[i][1];
            graph.get(src).add(dst);
            graph.get(dst).add(src);
        }
        distSum = new int[n];
        nodeNum = new int[n];
        Arrays.fill(nodeNum, 1);
        postOrder(0, -1);
        preOrder(0, -1);
        return distSum;
    }

    // 求 root 到子树所有节点的距离和
    private void postOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for(Integer neighbor : neighbors) {
            if(neighbor == parent)
                //如果邻接点是父节点，则跳过
                continue;
            postOrder(neighbor, root);
            nodeNum[root] += nodeNum[neighbor];
            distSum[root] += distSum[neighbor] + nodeNum[neighbor];
        }
    }

    // 根据 root 计算其邻居到所在子树之外的节点的距离和（包括root节点）
    private void preOrder(int root, int parent) {
        List<Integer> neighbors = graph.get(root);
        for(Integer neighbor : neighbors) {
            if(neighbor == parent)
                continue;
            distSum[neighbor] = distSum[root] - nodeNum[neighbor] + (graph.size() - nodeNum[neighbor]);
            preOrder(neighbor, root);
        }
    }
}
