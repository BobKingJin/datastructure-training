package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-05-28 15:34
 */
public class Number147 {

    // 参考：https://leetcode.cn/problems/insertion-sort-list/solution/dui-lian-biao-jin-xing-cha-ru-pai-xu-by-leetcode-s/
    public ListNode insertionSortList(ListNode head) {
        
        if (head == null) 
            return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastSorted = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummy;
                while (prev.next.val <= cur.val)
                    prev = prev.next;

                lastSorted.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = lastSorted.next;
        }
        return dummy.next;
    }
}
