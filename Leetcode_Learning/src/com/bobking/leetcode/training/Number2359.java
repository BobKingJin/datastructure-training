package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @Date: 2025/7/12 17:10
 * @Author: BobKing
 * @Description:
 */
public class Number2359 {

    // 参考: https://leetcode.cn/problems/find-closest-node-to-given-two-nodes/solutions/1710829/ji-suan-dao-mei-ge-dian-de-ju-chi-python-gr2u/?envType=daily-question&envId=2025-07-12
    public int closestMeetingNode1(int[] edges, int node1, int node2) {
        int[] dis1 = calcDis(edges, node1);
        int[] dis2 = calcDis(edges, node2);

        int n = edges.length;
        int minDis = n;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            int d = Math.max(dis1[i], dis2[i]);
            if (d < minDis) {
                minDis = d;
                ans = i;
            }
        }
        return ans;
    }

    private int[] calcDis(int[] edges, int x) {
        int n = edges.length;
        int[] dis = new int[n];
        // n 表示无法到达或者尚未访问的节点
        Arrays.fill(dis, n);
        // 从 x 出发, 直到无路可走(x = -1)或者重复访问节点(dis[x] < n)
        for (int d = 0; x >= 0 && dis[x] == n; x = edges[x]) {
            dis[x] = d++;
        }
        return dis;
    }

    // 参考: https://leetcode.cn/problems/find-closest-node-to-given-two-nodes/solutions/1710829/ji-suan-dao-mei-ge-dian-de-ju-chi-python-gr2u/?envType=daily-question&envId=2025-07-12
    public int closestMeetingNode2(int[] edges, int node1, int node2) {
        int n = edges.length;
        int ans = n;
        boolean[] visX = new boolean[n];
        boolean[] visY = new boolean[n];
        // x 或 y 没有访问过
        while (!visX[node1] || !visY[node2]) {
            // 标记访问过
            visX[node1] = visY[node2] = true;
            // x 到达 y 走过的路
            if (visY[node1]) {
                ans = node1;
            }
            // y 到达 x 走过的路
            if (visX[node2]) {
                // 如果有多个答案，返回最小的节点编号
                ans = Math.min(ans, node2);
            }
            if (ans < n) {
                return ans;
            }
            if (edges[node1] >= 0) {
                node1 = edges[node1];
            }
            if (edges[node2] >= 0) {
                node2 = edges[node2];
            }
        }
        return -1;
    }


}
