package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number310 {

    // 参考：https://leetcode-cn.com/problems/minimum-height-trees/solution/zui-rong-yi-li-jie-de-bfsfen-xi-jian-dan-zhu-shi-x/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        List<Integer> res = new ArrayList<Integer>();

        // 只有一个节点
        if (n == 1) {
            res.add(0);
            return res;
        }

        // 建立各个节点的出度表
        int[] degree = new int[n];

        // 建立图关系，在每个节点的 list 中存储相连节点
        List<List<Integer>> map = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++)
            map.add(new ArrayList<Integer>());

        for (int[] edge : edges) {
            degree[edge[0]]++;
            // 出度
            degree[edge[1]]++;
            // 添加相邻节点
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        // 建立队列
        Queue<Integer> queue = new LinkedList<Integer>();
        // 把所有出度为 1 的节点，也就是叶子节点入队
        for (int i = 0; i < n; i++)
            if (degree[i] == 1)
                queue.offer(i);

        while (!queue.isEmpty()) {

            // 这个地方注意，每层循环都要new一个新的结果集合，这样最后保存的就是最终的最小高度树了
            res = new ArrayList<Integer>();
            // 每一层的节点的数量
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                int cur = queue.poll();
                // 把当前节点加入结果集，不要有疑问，为什么当前只是叶子节点为什么要加入结果集呢?
                // 因为每次循环都会新建一个list，所以最后保存的就是最后一个状态下的叶子节点
                // 这也是很多题解里面所说的剪掉叶子节点的部分，可以想象一下图，每层遍历完
                // 都会把该层（也就是叶子节点层）这一层从队列中移除掉
                // 不就相当于把原来的图给剪掉一圈叶子节点，形成一个缩小的新的图吗?
                res.add(cur);
                List<Integer> neighbors = map.get(cur);
                // 把当前节点的相邻接点都拿出来
                // 把它们的出度都减 1，因为当前节点已经不存在了
                // 所以它的相邻节点们就有可能变成叶子节点
                for (int neighbor : neighbors) {
                    degree[neighbor]--;
                    if (degree[neighbor] == 1) {
                        // 如果是叶子节点就入队
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return res;
    }

}
