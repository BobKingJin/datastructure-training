package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2021-03-20 21:09
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
