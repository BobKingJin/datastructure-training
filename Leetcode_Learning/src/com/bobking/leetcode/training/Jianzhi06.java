package com.bobking.leetcode.training;

import java.util.ArrayList;

/**
 * @Date: 2026/6/29 16:35
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi06 {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        recursion(listNode, res);
        return res;
    }

    public void recursion(ListNode head, ArrayList<Integer> res) {
        if (head == null) {
            return;
        }
        recursion(head.next, res);
        res.add(head.val);
    }

}
