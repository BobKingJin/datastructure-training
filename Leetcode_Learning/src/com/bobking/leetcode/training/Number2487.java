package com.bobking.leetcode.training;

/**
 * @Date: 2024/1/9 7:22
 * @Author: BobKing
 * @Description:
 */
public class Number2487 {

    // 参考: https://leetcode.cn/problems/remove-nodes-from-linked-list/solutions/1993491/di-gui-jian-ji-xie-fa-by-endlesscheng-jfwi/?envType=daily-question&envId=2024-01-03
    public ListNode removeNodes1(ListNode head) {

        if (head.next == null)
            return head;

        // 返回的链表头一定是最大的
        ListNode node = removeNodes1(head.next);

        if (node.val > head.val)
            // 删除 head
            return node;
        // 不删除 head
        head.next = node;
        return head;
    }

    // 参考: https://leetcode.cn/problems/remove-nodes-from-linked-list/solutions/1993491/di-gui-jian-ji-xie-fa-by-endlesscheng-jfwi/?envType=daily-question&envId=2024-01-03
    public ListNode removeNodes2(ListNode head) {

        head = reverseList(head);
        ListNode cur = head;

        while (cur.next != null) {
            if (cur.val > cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return reverseList(head);
    }

    private ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }



}
