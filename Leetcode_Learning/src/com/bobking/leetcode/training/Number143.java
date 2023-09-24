package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-04-17 10:27
 */
public class Number143 {

    // 参考：https://leetcode-cn.com/problems/reorder-list/solution/zhong-pai-lian-biao-by-leetcode-solution/
    public void reorderList1(ListNode head) {

        if (head == null || head.next == null)
            return;

        List<ListNode> list = new ArrayList<ListNode>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0;
        int j = list.size() - 1;
        while (i < j) {

            list.get(i).next = list.get(j);
            i++;
            if (i == j)
                break;

            list.get(j).next = list.get(i);
            j--;
        }

        list.get(i).next = null;
    }

    // 参考：https://leetcode-cn.com/problems/reorder-list/solution/zhong-pai-lian-biao-by-leetcode-solution/
    public void reorderList2(ListNode head) {

        if (head == null || head.next == null)
            return;

        // 左半区最后一个节点
        ListNode mid = head;
        // 右半区第一个节点
        ListNode right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }

        right = mid.next;
        // 将左右半区断开
        mid.next = null;
        right = reverse(right);
        mergeLR(head, right);
    }

    // 反转右半部分
    private ListNode reverse(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    // 重新合并左右半区
    private void mergeLR(ListNode left, ListNode right) {

        ListNode next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }

        left.next = right;
    }
}
