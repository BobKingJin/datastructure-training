package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author BobKing
 * @create 2021-04-05 11:40
 */
public class Number116 {

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

    // 参考：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-2-4/
    public Node connect1(Node root) {

        if (root == null)
            return root;

        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1)
                    node.next = queue.peek();

                if (node.left != null)
                    queue.offer(root.left);
                if (node.right != null)
                    queue.offer(root.right);
            }
        }

        return root;
    }

    // 参考：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-2-4/
    public Node connect2(Node root) {

        if (root == null)
            return root;
        // 需要注意的一点是，当为第 N 层节点建立 next 指针时，处于第 N − 1 层
        // 当第 N 层节点的 next 指针全部建立完成后 N 层，建立第 N + 1 层节点的 next 指针
        Node leftMost = root;
        while (leftMost.left != null) {

            Node head = leftMost;

            while (head != null) {
                head.left.next = head.right;
                if (head.next != null)
                    head.right.next = head.next.left;
                head = head.next;
            }

            leftMost = leftMost.left;
        }

        return root;
    }

    // 参考：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/solution/tian-chong-mei-ge-jie-dian-de-xia-yi-ge-you-ce-2-4/
    public Node connect3(Node root) {

        if (root == null)
            return root;

        // 先序遍历
        if (root.left != null) {

            root.left.next = root.right;
            root.right.next = root.next == null ? null : root.next.left;
            connect3(root.left);
            connect3(root.right);
        }

        return root;
    }
}
