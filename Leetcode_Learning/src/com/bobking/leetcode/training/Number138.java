package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-03 21:14
 */
public class Number138 {

    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 参考：程序猿代码指南P63
    public Node copyRandomList(Node head) {

        if (head == null)
            return null;

        // 复制一份节点
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // 复制一份随机数节点
        cur = head;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 拆分
        cur = head;
        Node NodeHead = head.next;

        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next == null ? null : next.next;
            cur = next;
        }

        return NodeHead;
    }
}
