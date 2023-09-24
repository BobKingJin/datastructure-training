package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-11-06 10:10
 */
public class Number2385 {

    // 最短用时
    int ans = 0;
    // 起始节点的高度
    int depth = -1;

    // 参考：https://leetcode.cn/problems/amount-of-time-for-binary-tree-to-be-infected/solution/java-dfs-by-backtraxe-5ov9/
    public int amountOfTime1(TreeNode root, int start) {
        dfs1(root, 0, start);
        return ans;
    }

    private int dfs1(TreeNode root, int level, int start) {

        if (root == null)
            return 0;

        if (root.val == start)
            // 当前节点即起始节点
            depth = level;

        // 左子树的树高
        int l = dfs1(root.left, level + 1, start);
        // 起始节点是否在左子树上
        boolean inLeft = depth != -1;
        // 右子树的树高
        int r = dfs1(root.right, level + 1, start);
        // 情况1：感染以 start 为根结点的树所需时间
        if (root.val == start)
            ans = Math.max(ans, Math.max(l, r));
        // 情况2：感染以 root 为根结点的树所需时间
        if (inLeft) {
            ans = Math.max(ans, depth - level + r);
        } else {
            ans = Math.max(ans, depth - level + l);
        }
        // 返回树高
        return Math.max(l, r) + 1;
    }

    HashMap<TreeNode, TreeNode> map;
    int start;
    TreeNode s;
    int res = -1;

    public int amountOfTime2(TreeNode root, int start) {

        map = new HashMap<TreeNode, TreeNode>();
        this.start = start;
        // 找到 传染节点 并记录每个节点的父节点
        dfs2(null, root);

        // bfs 记录 传染时间
        Set<TreeNode> vis = new HashSet<TreeNode>();
        vis.add(s);
        vis.add(null);
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(s);

        while(!q.isEmpty()){

            res++;
            int len = q.size();
            while(len-- > 0){

                TreeNode node = q.poll();
                if (!vis.contains(node.left)) {
                    vis.add(node.left);
                    q.add(node.left);
                }
                if (!vis.contains(node.right)) {
                    vis.add(node.right);
                    q.add(node.right);
                }
                if (!vis.contains(map.get(node))) {
                    vis.add(map.get(node));
                    q.add(map.get(node));
                }
            }
        }
        return res;
    }

    private void dfs2(TreeNode p, TreeNode node){

        if(node == null)
            return;

        if(node.val == start)
            s = node;

        map.put(node, p);
        dfs2(node, node.left);
        dfs2(node, node.right);
    }

}
