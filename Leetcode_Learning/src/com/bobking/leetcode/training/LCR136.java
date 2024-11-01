package com.bobking.leetcode.training;

/**
 * @Date: 2024/10/31 18:01
 * @Author: BobKing
 * @Description:
 */
public class LCR136 {

    public ListNode deleteNode(ListNode head, int val) {

        if (head.val == val)
            return head.next;

        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null)
            pre.next = cur.next;
        return head;
    }
}
