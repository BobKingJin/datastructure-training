package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-03 17:07
 */
public class Number328 {

    // 参考：https://leetcode-cn.com/problems/odd-even-linked-list/solution/qi-ou-lian-biao-by-leetcode-solution/
    public ListNode oddEvenList(ListNode head) {

        if (head == null)
            return head;

        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

}
