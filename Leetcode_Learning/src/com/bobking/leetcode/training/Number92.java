package com.bobking.leetcode.training;

public class Number92 {

    // 参考：程序猿代码指南P48
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left < 1 || right < 1 || left > right)
            return head;

        int len = 0;
        ListNode node1 = head;
        // left 的前一个节点
        ListNode fPre = null;
        // right 的下一个节点
        ListNode tPos = null;
        while (node1 != null) {

            len++;
            fPre = len == left - 1 ? node1 : fPre;
            tPos = len == right + 1 ? node1 : tPos;
            node1 = node1.next;
        }

        // 有可能 left 为 1，那么 fPre = null，即可能存在换头
        node1 = fPre == null ? head : fPre.next;
        ListNode node2 = node1.next;
        node1.next = tPos;
        ListNode next = null;
        while (node2 != tPos) {

            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }

        if (fPre != null) {
            fPre.next = node1;
            return head;
        } else {
            return node1;
        }
    }
}
