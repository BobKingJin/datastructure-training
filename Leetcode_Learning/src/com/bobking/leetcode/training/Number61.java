package com.bobking.leetcode.training;

public class Number61 {

    // 参考：https://leetcode-cn.com/problems/rotate-list/solution/fu-xue-ming-zhu-wen-ti-chai-fen-fen-xian-z4dr/
    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0)
            return head;

        // 将链表每个节点向右移动 k 个位置，相当于把链表的后面 k % len 个节点移到链表的最前面
        // 找出倒数第 k + 1 个节点
        ListNode cur = head;
        // 统计链表长度
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 对 k 化简
        k %= len;
        // 不用改变
        if (k == 0)
            return head;

        // 快指针 fast 先走 k 步
        ListNode fast = head;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        // 快慢指针再一起同步前进，直至 fast 走到尾节点停
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 此时的慢指针 slow 的下一个节点就是旋转后的新头，原尾节点 fast 串连到老头 head 上
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
    }
}
