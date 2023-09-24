package com.bobking.leetcode.training;

public class Number82 {

    // 参考：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/fu-xue-ming-zhu-di-gui-die-dai-yi-pian-t-wy0h/
    // 递归函数：删除以 head 作为开头的有序链表中，值出现重复的节点
    public ListNode deleteDuplicates1(ListNode head) {

        // 没有节点或者只有一个节点，必然没有重复元素
        if (head == null || head.next == null)
            return head;

        // 当前节点和下一个节点，值不同，则 head 的值是需要保留的，对 head.next 继续递归
        if (head.val != head.next.val) {
            head.next = deleteDuplicates1(head.next);
            return head;
        } else {
            // 当前节点与下一个节点的值重复了，重复的值都不能要
            // 一直往下找，找到不重复的节点。返回对不重复节点的递归结果
            ListNode notDup = head.next.next;
            while (notDup != null && notDup.val == head.val)
                notDup = notDup.next;

            return deleteDuplicates1(notDup);
        }
    }

    // 参考：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/fu-xue-ming-zhu-di-gui-die-dai-yi-pian-t-wy0h/
    public ListNode deleteDuplicates2(ListNode head) {

        if (head == null || head.next == null)
            return head;
        // 维护一个不变的节点 dummy
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            // 相等的节点则跳过，走到相同值元素的最后一步
            while (cur.next != null && cur.next.val == cur.val)
                cur = cur.next;
            // 如果 pre 和 cur 之间没有重复节点，pre 后移
            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}
