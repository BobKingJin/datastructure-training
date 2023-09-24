package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2022-08-05 14:52
 */
public class Number547 {

    // 参考：https://leetcode.cn/problems/number-of-provinces/solution/sheng-fen-shu-liang-by-leetcode-solution-eyk0/
    public int findCircleNum1(int[][] isConnected) {

        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;

        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, cities, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {

        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(isConnected, visited, cities, j);
            }
        }
    }

    // 参考：https://leetcode.cn/problems/number-of-provinces/solution/sheng-fen-shu-liang-by-leetcode-solution-eyk0/
    public int findCircleNum2(int[][] isConnected) {

        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < cities; k++) {
                        if (isConnected[j][k] == 1 && !visited[k])
                            queue.offer(k);
                    }
                }
                provinces++;
            }
        }

        return provinces;
    }

    private class UnionFind {

        // 记录父节点
        private Map<Integer, Integer> father;
        // 记录集合的数量
        private int numOfSets = 0;

        public UnionFind() {
            father = new HashMap<Integer, Integer>();
            numOfSets = 0;
        }

        public void add(int x) {
            if (!father.containsKey(x)) {
                father.put(x, null);
                numOfSets++;
            }
        }

        public void merge(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                father.put(rootX, rootY);
                numOfSets--;
            }
        }

        public int find(int x) {

            int root = x;

            while (father.get(root) != null)
                root = father.get(root);

            while (x != root) {
                int originalFather = father.get(x);
                father.put(x, root);
                x = originalFather;
            }

            return root;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public int getNumOfSets() {
            return numOfSets;
        }
    }

    // 参考：https://leetcode.cn/problems/number-of-provinces/solution/python-duo-tu-xiang-jie-bing-cha-ji-by-m-vjdr/
    public int findCircleNum3(int[][] isConnected) {

        UnionFind uf = new UnionFind();
        for (int i = 0; i < isConnected.length; i++) {
            uf.add(i);
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1)
                    uf.merge(i, j);
            }
        }

        return uf.getNumOfSets();
    }
}
