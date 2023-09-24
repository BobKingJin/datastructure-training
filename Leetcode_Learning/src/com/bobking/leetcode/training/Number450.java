package com.bobking.leetcode.training;

public class Number450 {

    // 参考：https://leetcode-cn.com/problems/same-tree/solution/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/
    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null)
            return null;

        if (root.val == key) {

            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            // 要么找到左子树的最大节点，要么找到右子树的最小节点
            // 找到 root 节点的右子树的最小节点
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            // 同时在右子树中删除 minNode 节点
            root.right = deleteNode(root.right, minNode.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    private TreeNode getMin(TreeNode node) {
        // 因为是 BST，所以左子树的最左节点即是左子树的最小节点，找到 node节点的左子树中的最左节点
        while (node.left != null)
            node = node.left;
        return node;
    }

}
