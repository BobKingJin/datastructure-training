package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number2130 {

    // 参考：https://leetcode.cn/problems/maximum-twin-sum-of-a-linked-list/solution/shuang-zhou-sai-t2-qu-qiao-zuo-fa-zheng-nkfa9/
    public int pairSum1(ListNode head) {

        List<Integer> list = new ArrayList<Integer>();

        // 将所有元素都添加到 list 中
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        int ans = 0;
        // 根据下标取值
        for (int i = 0; i < list.size() / 2; i++)
            ans = Math.max(ans, list.get(i) + list.get(list.size() - i - 1));

        return ans;
    }

    // 参考：https://leetcode.cn/problems/maximum-twin-sum-of-a-linked-list/solution/shuang-zhou-sai-t2-qu-qiao-zuo-fa-zheng-nkfa9/
    public int pairSum2(ListNode head) {

        // 快慢指针求链表的中点，求完后 slow 为后一半元素的第一个
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 经典双指针方法：反转后一半的链表元素
        ListNode pre = null;
        ListNode p = slow;
        while (p != null) {
            ListNode tmp = p.next;
            p.next = pre;
            pre = p;
            p = tmp;
        }

        int ans = 0;
        ListNode q;
        // 从前一半元素首元素和后一半尾元素向中间遍历
        for (p = head, q = pre; q != null; p = p.next, q = q.next)
            ans = Math.max(ans, p.val + q.val);

        return ans;
    }
}
