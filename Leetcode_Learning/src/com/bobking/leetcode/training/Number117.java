package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-09-10 21:51
 */
public class Number117 {

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    // 参考：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/bfsjie-jue-zui-hao-de-ji-bai-liao-100de-yong-hu-by/
    public Node connect1(Node root) {

        if (root == null)
            return root;

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {

            Node pre = null;
            for (int i = 0; i < queue.size(); i++) {

                Node node = queue.poll();
                // 如果 pre 为空就表示 node 节点是这一行的第一个
                if (pre != null)
                    pre.next = node;

                // 然后再让当前节点成为前一个节点
                pre = node;
                // 左右子节点如果不为空就入队
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return root;
    }

    // 参考：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/bfsjie-jue-zui-hao-de-ji-bai-liao-100de-yong-hu-by/
    public Node connect2(Node root) {

        if (root == null)
            return root;

        Node cur = root;
        // 例如：当访问第二行时，是把第三行节点连接起来
        while (cur != null) {
            // 每一层都先创建一个 fakeNode
            Node dummy = new Node(0);
            Node pre = dummy;

            while (cur != null) {

                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }

                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }

                cur = cur.next;
            }
            cur = dummy.next;
        }

        return root;
    }


}
