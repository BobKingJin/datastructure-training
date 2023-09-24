package com.bobking.leetcode.training;

public class Number24 {

    // 参考：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/hua-jie-suan-fa-24-liang-liang-jiao-huan-lian-biao/
    public ListNode swapPairs1(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode next = head.next;
        // 递归
        head.next = swapPairs1(next.next);
        next.next = head;
        return next;
    }

    // 参考：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/hua-jie-suan-fa-24-liang-liang-jiao-huan-lian-biao/
    // 参考：程序猿代码指南P74
    public ListNode swapPairs2(ListNode head) {

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }

        return pre.next;
    }

}
