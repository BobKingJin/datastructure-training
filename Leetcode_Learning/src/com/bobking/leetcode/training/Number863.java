package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-08-20 18:25
 */
public class Number863 {

    // 根据数据范围最多有 501 个点，每个点最多有 2 条无向边（两个子节点）
    int N = 510;
    int M = N * 4;
    int[] he = new int[N];
    int[] e = new int[M];
    int[] ne = new int[M];
    int idx;

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }

    boolean[] vis = new boolean[N];

    // 参考：https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree/solution/gong-shui-san-xie-yi-ti-shuang-jie-jian-x6hak/
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        // 首先 idx 是用来对边进行编号的，然后对存图用到的几个数组作简单解释：
        // he 数组：存储是某个节点所对应的边的集合（链表）的头节点
        // e 数组：由于访问某一条边指向的节点
        // ne 数组：由于是以链表的形式进行存边，该数组就是用于找到下一条边
        // 当想要遍历所有由 a 点发出的边时，可以使用如下方式
        // for (int i = he[a]; i != -1; i = ne[i]) {
        //     int j = e[i]; // 存在由 a 指向 j 的边
        // }
        // 数组 he 的下标表示节点，值是一个索引 ind，e[ind] 表示对应一条边，ne[ind] 表示下一个连接节点的索引
        // 假设与 节点a 相连的节点有 b, c, 那么通过 he[a]取得一个索引 ind1 后，通过 e[ind1] = b 可以得到与 a 相连的第一个节点是 b
        // 然后通过 ne[ind1] 可以获得下一个节点的索引 ind2 ，通过 e[ind2] = c 可以得到与 a 相连的第二个节点是 c，最后 ne[ind2] = -1 说明没有下一个节点了

        // add函数采用链表的头插法，假设 节点a 已经有一个相连的节点 b，那么就有 he[a] = ind, e[ind] = b ，此时再给 a 增加一个相连的节点 c
        // 那么就要建立由 b 的索引到新节点 c 的索引 ne[new_ind] = he[a] = ind ，然后新建一条边 e[new_ind], 最后更新 he[a] = new_ind
        // 就完成了由 a -> b 到 a -> c -> b 的添加操作
        // 可以理解为 he 是邻接表的表头，key 是节点 val 是一个指向存有相邻节点的链表头指针，e是链表节点的 val 即相邻节点，ne是链表节点的 next 指针

        List<Integer> ans = new ArrayList<Integer>();
        Arrays.fill(he, -1);
        dfs(root);
        Deque<Integer> d = new ArrayDeque<Integer>();
        d.addLast(target.val);
        vis[target.val] = true;

        while (!d.isEmpty() && k >= 0) {
            int size = d.size();
            while (size-- > 0) {
                int poll = d.pollFirst();
                if (k == 0) {
                    ans.add(poll);
                    continue;
                }
                for (int i = he[poll]; i != -1 ; i = ne[i]) {
                    int j = e[i];
                    if (!vis[j]) {
                        d.addLast(j);
                        vis[j] = true;
                    }
                }
            }
            k--;
        }
        return ans;
    }

    private void dfs(TreeNode root) {

        if (root == null)
            return;

        if (root.left != null) {
            add(root.val, root.left.val);
            add(root.left.val, root.val);
            dfs(root.left);
        }

        if (root.right != null) {
            add(root.val, root.right.val);
            add(root.right.val, root.val);
            dfs(root.right);
        }
    }
}
