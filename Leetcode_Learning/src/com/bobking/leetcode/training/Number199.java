package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-07-24 20:32
 */
public class Number199 {

    // 参考：程序猿代码指南P132
    // 对比Number102
    public List<Integer> rightSideView1(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        // last 为当前行的最右节点
        TreeNode last = root;
        // nLast 为下一行的最右节点
        TreeNode nLast = null;
        // 利用队列实现 bfs
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            root = queue.poll();

            if (root.left != null) {
                queue.offer(root.left);
                nLast = root.left;
            }
            if (root.right != null) {
                queue.offer(root.right);
                nLast = root.right;
            }

            if (root == last) {
                res.add(last.val);
                last = nLast;
            }
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/jian-dan-bfsdfs-bi-xu-miao-dong-by-sweetiee/
    public List<Integer> rightSideView2(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();

        if (root == null)
            return res;

        // 利用队列实现 bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                // 将当前层的最后一个节点放入结果列表
                if (i == size - 1)
                    res.add(node.val);
            }
        }
        return res;
    }

    List<Integer> res = new ArrayList<>();

    // 参考：https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/jian-dan-bfsdfs-bi-xu-miao-dong-by-sweetiee/
    // dfs
    public List<Integer> rightSideView3(TreeNode root) {

        if (root == null)
            return res;
        // 从根节点开始访问，根节点深度是 0
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth) {

        if (root == null)
            return;

        // 先访问 当前节点，再递归地访问 右子树 和 左子树
        // 如果当前节点所在深度还没有出现在 res 里
        // 说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入 res 中
        if (depth == res.size())
            res.add(root.val);

        depth++;
        // 先访问右子树
        dfs(root.right, depth);
        // 再访问左子树
        dfs(root.left, depth);
    }
}