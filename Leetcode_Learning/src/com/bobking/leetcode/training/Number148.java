package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-20 23:29
 */
public class Number148 {

    // 参考：https://leetcode-cn.com/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
    public ListNode sortList1(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList1(head);
        ListNode right = sortList1(tmp);
        // 创建一个假头节点
        ListNode node = new ListNode(0);
        ListNode res = node;

        while (left != null && right != null) {
            if (left.val > right.val) {
                node.next = right;
                right = right.next;
            } else {
                node.next = left;
                left = left.next;
            }
            node = node.next;
        }

        node.next = left == null ? right : left;
        return res.next;
    }

    // 参考：程序猿代码指南P84
    public ListNode sortList2(ListNode head) {

        // 排序部分尾部
        ListNode tail = null;
        // 未排序部分头部
        ListNode cur = head;
        // 最小节点
        ListNode small = null;
        // 最小节点的前一个节点
        ListNode smallPre = null;

        while (cur != null) {
            small = cur;
            smallPre = getSmallPreNode(cur);
            if (smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }
            // 如果 smallPre 为空，说明 cur(当前节点) 即为最小值节点
            cur = (cur == small) ? cur.next : cur;

            if (tail == null) {
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;
        }
        return head;
    }

    private ListNode getSmallPreNode(ListNode head) {

        ListNode small = head;
        ListNode smallPre = null;
        ListNode cur = head.next;
        ListNode pre = head;

        while (cur != null) {
            if (cur.val < small.val) {
                small = cur;
                smallPre = pre;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return smallPre;
    }
}
