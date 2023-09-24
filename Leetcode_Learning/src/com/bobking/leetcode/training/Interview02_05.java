package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-24 8:25
 */
public class Interview02_05 {

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        
        ListNode head = new ListNode(0);
        ListNode prev = head;
        int ca = 0;
        while (l1 != null || l2 != null || ca != 0) {
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + ca;
            ca = sum / 10;
            prev.next = new ListNode(sum % 10);
            prev = prev.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }
        return head.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        recusion(head, l1, l2, 0);
        return head.next;
    }

    private void recusion(ListNode res, ListNode l1, ListNode l2, int ca) {
        
        if (l1 == null && l2 == null && ca == 0)
            return;
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + ca;
        res.next = new ListNode(0);
        res.next.val = sum % 10;
        ca = sum / 10;
        recusion(res.next, l1 != null ? l1.next : null, l2 != null ? l2.next : null, ca);
    }
}
