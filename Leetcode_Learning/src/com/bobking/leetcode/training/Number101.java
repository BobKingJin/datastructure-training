package com.bobking.leetcode.training;

import java.util.LinkedList;

/**
 * @author BobKing
 * @create 2021-07-09 17:40
 */
public class Number101 {

    // 参考：https://leetcode-cn.com/problems/symmetric-tree/solution/dong-hua-yan-shi-101-dui-cheng-er-cha-shu-by-user7/
    public boolean isSymmetric1(TreeNode root) {

        if (root == null)
            return true;

        // 调用递归函数，比较左节点，右节点
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode left, TreeNode right) {

        // 递归的终止条件是两个节点都为空，或者两个节点中有一个为空，或者两个节点的值不相等
        if (left == null && right == null)
            return true;
        // 有一个节点不为空
        if (left == null || right == null)
            return false;
        if (left.val != right.val)
            return false;
        // 再递归的比较 左节点的左孩子 和 右节点的右孩子
        // 以及比较  左节点的右孩子 和 右节点的左孩子
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

    // 参考：https://leetcode-cn.com/problems/symmetric-tree/solution/dong-hua-yan-shi-101-dui-cheng-er-cha-shu-by-user7/
    // bfs
    public boolean isSymmetric2(TreeNode root) {

        if (root == null || (root.left == null && root.right == null))
            return true;

        // 用队列保存节点
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        // 将根节点的左右孩子放到队列中, 一开始就压入两个节点
        queue.add(root.left);
        queue.add(root.right);
        while (queue.size() > 0) {
            // 从队列中取出两个节点, 再比较这两个节点
            TreeNode left = queue.removeFirst();
            TreeNode right = queue.removeFirst();
            // 如果两个节点都为空就继续循环, 两者有一个为空就返回 false
            if (left == null && right == null)
                continue;
            if (left == null || right == null)
                return false;
            if (left.val != right.val)
                return false;
            // 将左节点的左孩子, 右节点的右孩子放入队列
            queue.add(left.left);
            queue.add(right.right);
            // 将左节点的右孩子, 右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }

        return true;
    }

}
