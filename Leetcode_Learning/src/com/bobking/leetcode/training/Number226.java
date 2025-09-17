package com.bobking.leetcode.training;

import java.util.LinkedList;

/**
 * @author BobKing
 * @create 2021-07-09 18:16
 */
public class Number226 {

    // dfs 注意对比方法一和方法二的思路

    // 参考：https://leetcode-cn.com/problems/invert-binary-tree/solution/dong-hua-yan-shi-liang-chong-shi-xian-226-fan-zhua/
    // 从上到下递归
    public TreeNode invertTree1(TreeNode root) {

        // 递归函数的终止条件，节点为空时返回
        if (root == null) {
            return null;
        }

        // 将当前节点的左右子树交换
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        // 递归交换当前节点的 左子树
        invertTree1(root.left);
        // 递归交换当前节点的 右子树
        invertTree1(root.right);
        return root;
    }

    // 参考：https://leetcode-cn.com/problems/invert-binary-tree/solution/fan-zhuan-er-cha-shu-by-leetcode-solution/
    // 从下到上递归
    public TreeNode invertTree2(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode left = invertTree2(root.left);
        TreeNode right = invertTree2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 参考：https://leetcode-cn.com/problems/invert-binary-tree/solution/dong-hua-yan-shi-liang-chong-shi-xian-226-fan-zhua/
    public TreeNode invertTree3(TreeNode root) {

        if (root == null) {
            return null;
        }

        // 将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每次都从队列中拿一个节点，并交换这个节点的左右子树
            TreeNode tmp = queue.poll();
            TreeNode left = tmp.left;
            tmp.left = tmp.right;
            tmp.right = left;
            // 如果当前节点的左子树不为空，则放入队列等待后续处理
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            // 如果当前节点的右子树不为空，则放入队列等待后续处理
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
        }
        // 返回处理完的根节点
        return root;
    }


}
