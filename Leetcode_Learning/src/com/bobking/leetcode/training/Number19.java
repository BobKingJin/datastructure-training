package com.bobking.leetcode.training;

public class Number19 {

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {

        if (head == null || n < 1)
            return head;

        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        // 倒数第 n 个节点即是顺数第 length - n + 1 个节点
        for (int i = 1; i < length - n + 1; ++i)
            cur = cur.next;

        cur.next = cur.next.next;
        return dummy.next;
    }

    // 参考：程序猿代码指南P42
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        if (head == null || n < 1)
            return head;

        ListNode cur = head;
        while (cur != null) {
            n--;
            cur = cur.next;
        }

        if (n == 0)
            return head.next;

        if (n < 0) {
            cur = head;
            while (++n != 0)
                cur = cur.next;
            cur.next = cur.next.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {

        // 由于可能会删除链表头部, 用哨兵节点简化代码
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = dummy;

        while (n-- > 0)
            // 右指针先向右走 n 步
            right = right.next;

        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        // 左指针的下一个节点就是倒数第 n 个节点
        left.next = left.next.next;
        return dummy.next;
    }
}
