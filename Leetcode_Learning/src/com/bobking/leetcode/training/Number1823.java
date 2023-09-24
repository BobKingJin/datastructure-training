package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-04 15:31
 */
public class Number1823 {

    // 参考：https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/solution/by-ac_oier-qsuq/
    public int findTheWinner1(int n, int k) {

        // 创建一个标记数组 vis，若有 vis[idx] = true 则代表点编号为 idx 已被淘汰
        // 每次都从当前位置 cur 开始，找到第 k 个尚未淘汰的点（vis[idx] = false）
        // 并将其进行标记（vis[idx] = true），共有 n - 1 个点需要被淘汰

        boolean[] vis = new boolean[n + 10];
        int cnt = 0;
        int cur = 0;

        while (cnt != n - 1) {
            for (int j = 0; j < k - 1; j++) {
                cur++;
                while (vis[cur % n])
                    cur++;
            }
            vis[cur % n] = true;
            cnt++;
            cur++;
            while (vis[cur % n])
                cur++;
        }

        return (cur % n) + 1;
    }

    // 参考：https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/solution/by-ac_oier-qsuq/
    public int findTheWinner2(int n, int k) {

        // 每次往同一方向，以固定步长 k 进行消数。由于下一次操作的发起点为消除位置的下一个点（即前后两次操作发起点在原序列下标中相差 k）
        // 同时问题规模会从 n 变为 n - 1，因此原问题答案等价于 findTheWinner(n - 1, k) + k

        if (n <= 1) 
            return n;
        
        int res = (findTheWinner2(n - 1, k) + k) % n;
        
        return res == 0 ? n : res;
    }
    
}
