package com.bobking.leetcode.training;

/**
 * @Date: 2026/6/28 21:51
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi22 {

    // 参考: Number19
    public ListNode FindKthToTail(ListNode pHead, int k) {
        ListNode fast = pHead;
        ListNode slow = pHead;
        // 快指针先行k步
        for (int i = 0; i < k; i++) {
            if (fast != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }
        // 快慢指针同步，快指针先到底，慢指针指向倒数第k个
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
