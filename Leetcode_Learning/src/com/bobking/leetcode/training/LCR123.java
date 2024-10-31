package com.bobking.leetcode.training;

import java.util.ArrayList;

/**
 * @Date: 2024/10/31 16:40
 * @Author: BobKing
 * @Description:
 */
public class LCR123 {

    ArrayList<Integer> tmp = new ArrayList<Integer>();

    public int[] reverseBookList(ListNode head) {

        if (head == null) {
            return new int[0];
        }

        recursion(head);
        int[] res = new int[tmp.size()];
        for (int i = 0; i < res.length; i++)
            res[i] = tmp.get(i);
        return res;
    }

    private void recursion(ListNode head) {
        if (head == null)
            return;
        recursion(head.next);
        tmp.add(head.val);
    }
}
