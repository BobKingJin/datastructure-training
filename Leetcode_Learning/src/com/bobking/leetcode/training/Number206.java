package com.bobking.leetcode.training;

public class Number206 {

    // 参考：程序猿代码指南P47
    public ListNode reverseList1(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode next = null;
        ListNode pre = null;

        while (head != null) {

            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode cur = reverseList2(head.next);
        // 如果链表是 1 -> 2 -> 3 -> 4 -> 5，那么此时的 cur 就是 5
        // 而 head 是 4，head 的下一个是 5，下下一个是空
        // 所以 head.next.next 就是 5 -> 4
        head.next.next = head;
        // 防止链表循环，需要将 head.next 设置为空
        head.next = null;
        return cur;
    }
}
