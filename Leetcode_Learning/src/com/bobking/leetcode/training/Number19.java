package com.bobking.leetcode.training;

public class Number19 {

    public ListNode removeNthFromEnd1(ListNode head, int n) {

        if (head == null || n < 1)
            return head;

        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        node = head;
        // 倒数第 n 个节点即是顺数第 length - n + 1 个节点
        // 从头节点走 step 步
        int step = length - n - 1;
        while (step > 0) {
            node = node.next;
            step--;
        }

        node.next = node.next.next;
        return head;
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
}
