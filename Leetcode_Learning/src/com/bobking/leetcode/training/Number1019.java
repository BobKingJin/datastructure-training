package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author BobKing
 * @create 2022-09-17 11:09
 */
public class Number1019 {

    public int[] nextLargerNodes1(ListNode head) {

        List<Integer> list = new ArrayList<Integer>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int[] res = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            while (!stack.empty() && list.get(stack.peek()) < list.get(i)) {

                int index = stack.pop();
                res[index] = list.get(i);
            }
            stack.push(i);
        }
        return res;
    }

    int[] ans;

    public int[] nextLargerNodes2(ListNode head) {
        calNode(head, 0, new Stack<Integer>());
        return ans;
    }

    public void calNode(ListNode node, int index, Stack<Integer> stack) {
        if (node == null) {
            ans = new int[index];
            return;
        }
        calNode(node.next, index + 1, stack);
        while (!stack.empty() && stack.peek() <= node.val)
            stack.pop();
        ans[index] = stack.empty() ? 0 : stack.peek();
        stack.push(node.val);
    }


}
