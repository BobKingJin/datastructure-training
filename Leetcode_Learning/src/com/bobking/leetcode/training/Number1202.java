package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-09-24 20:03
 */
public class Number1202 {

    // 参考：https://leetcode.cn/problems/smallest-string-with-swaps/solution/1202-jiao-huan-zi-fu-chuan-zhong-de-yuan-wgab/
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

        // 需要找出同属于一个连通分量的所有字符。把「连在一起」的索引按照字符的 ASCII 值升序排序
        // 交换关系具有传递性、找哪些索引连在一起、数组 pairs 给出的是数对的形式，这三点提示可以使用并查集

        // 并查集管理的是「索引」不是「字符」

        if (pairs.size() == 0)
            return s;

        // 第 1 步：将任意交换的节点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<Integer, PriorityQueue<Character>>(len);

        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if (hashMap.containsKey(root)) {
                hashMap.get(root).offer(charArray[i]);
            } else {
                PriorityQueue<Character> minHeap = new PriorityQueue<Character>();
                minHeap.offer(charArray[i]);
                hashMap.put(root, minHeap);
            }
            // 上面六行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
            // hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }

        // 第 3 步：重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }

        return stringBuilder.toString();
    }

    private class UnionFind {

        private int[] parent;

        // 以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y) {

            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY)
                return;

            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度仅加了 1
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                // 此时以 rootY 为根结点的树的高度不变
            } else {
                // 同理，此时以 rootX 为根结点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        public int find(int x) {

            if (x != parent[x])
                parent[x] = find(parent[x]);

            return parent[x];
        }
    }
}
