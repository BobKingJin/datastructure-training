package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-31 11:57
 */
public class Number1669 {

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        ListNode p = list1;
        ListNode q = list1;
        while (--a > 0)
            p = p.next;

        while (b-- > 0)
            q = q.next;

        p.next = list2;
        while (p.next != null)
            p = p.next;

        p.next = q.next;
        q.next = null;
        return list1;
    }
}
