package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-06-05 10:36
 */
public class Jianzhi36 {

    // 参考：程序猿代码指南P82
    public Node treeToDoublyList1(Node root) {

        if (root == null)
            return root;

        Queue<Node> queue = new LinkedList<Node>();
        // 因为要将 BST 转为有序的双向链表，所以这里进行中序遍历
        inOrderToQueue(root, queue);

        root = queue.poll();
        Node pre = root;
        // pre.left = null;
        Node cur = null;

        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }

        // pre.right = null;
        root.left = pre;
        pre.right = root;
        return root;
    }

    // 中序遍历
    private void inOrderToQueue(Node node, Queue<Node> queue) {

        if (node == null)
            return;

        inOrderToQueue(node.left, queue);
        queue.offer(node);
        inOrderToQueue(node.right, queue);
    }

    private class ReturnType {

        public Node start;
        public Node end;

        public ReturnType(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    // 参考：程序猿代码指南P82
    public Node treeToDoublyList2(Node root) {

        if (root == null)
            return root;

        ReturnType res = recursion(root);
        res.start.left = res.end;
        res.end.right = res.start;

        return res.start;
    }

    // 将以 node 为头节点的二叉树转为双向链表
    // 返回值为双向链表的头节点和尾节点
    private ReturnType recursion(Node node) {

        if (node == null)
            return new ReturnType(null, null);
        // 因为 recursion 是将以 node 为头节点的二叉树转为双向链表
        // 所以才采用后序遍历，即先处理左子树，然后处理右子树
        // 后序遍历
        ReturnType left = recursion(node.left);
        ReturnType right = recursion(node.right);

        if (left.end != null)
            left.end.right = node;
        node.left = left.end;
        node.right = right.start;
        if (right.start != null)
            right.start.left = node;

        /*
        * 例如：
        *           1
        *        2     3
        * 当遍历到 2 时， left.start = null, left.right = null，最后返回的是 new ReturnType(2, 2)
        * 当遍历到 3 时， left.start = null, left.right = null，最后返回的是 new ReturnType(3, 3)
        * 然后返回到 1， 那么对于节点 1 的 left = (2, 2) right = (3, 3)
        * 那么 2.right = 1, 1.left = 2, 1.righ = 3, 3.left = 1
        * */
        return new ReturnType(left.start != null ? left.start : node,
                right.end != null ? right.end : node);
    }

}
