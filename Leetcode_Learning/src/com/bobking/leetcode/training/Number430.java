package com.bobking.leetcode.training;

public class Number430 {

    private class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(Integer val){
            this.val = val;
        }
    }

    // 参考：https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/solution/gong-shui-san-xie-yi-ti-shuang-jie-di-gu-9wfz/
    public Node flatten1(Node head) {

        Node dummy = new Node(0);
        dummy.next = head;

        while (head != null) {
            if (head.child == null) {
                head = head.next;
            } else {
                Node tmp = head.next;
                Node chead = flatten1(head.child);
                head.next = chead;
                chead.prev = head;
                head.child = null;

                while (head.next != null)
                    head = head.next;

                head.next = tmp;
                if (tmp != null)
                    tmp.prev = head;

                head = tmp;
            }
        }
        return dummy.next;
    }

    // 参考：https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/solution/gong-shui-san-xie-yi-ti-shuang-jie-di-gu-9wfz/
    public Node flatten2(Node head) {
        dfs(head);
        return head;
    }

    private Node dfs(Node head) {

        Node last = head;

        while (head != null) {
            if (head.child == null) {
                last = head;
                head = head.next;
            } else {
                Node tmp = head.next;
                Node childLast = dfs(head.child);
                head.next = head.child;
                head.child.prev = head;
                head.child = null;

                if (childLast != null)
                    childLast.next = tmp;

                if (tmp != null)
                    tmp.prev = childLast;

                last = head;
                head = childLast;
            }
        }

        return last;
    }

    // 参考：https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/solution/gong-shui-san-xie-yi-ti-shuang-jie-di-gu-9wfz/
    public Node flatten3(Node head) {

        Node dummy = new Node(0);
        dummy.next = head;

        for (; head != null; head = head.next) {
            if (head.child != null) {

                Node tmp = head.next;
                Node child = head.child;
                head.next = child;
                child.prev = head;
                head.child = null;
                Node last = head;

                while (last.next != null)
                    last = last.next;

                last.next = tmp;

                if (tmp != null)
                    tmp.prev = last;
            }
        }

        return dummy.next;
    }
}
