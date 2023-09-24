package com.bobking.leetcode.training;

public class Number510 {

    private class Node {

        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    // 参考：程序猿代码指南P153
    public Node inorderSuccessor(Node node) {

        if (node == null)
            return node;

        // node 有右子树，则找到右子树的最左节点
        if (node.right != null) {
            return getLeftMostNode(node.right);
        } else {
            // node 没有右子树，则先判断该节点是否是其父节点的左子树
            Node parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }

            return parent;
        }
    }

    private Node getLeftMostNode(Node node) {

        if (node == null)
            return node;

        while (node.left != null)
            node = node.left;

        return node;
    }
}
