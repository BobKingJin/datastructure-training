package com.bobking.leetcode.training;


/**
 * @author BobKing
 * @create 2020-11-08 8:49
 */
public class Number2 {

    // 参考：https://leetcode-cn.com/problems/add-two-numbers/solution/hua-jie-suan-fa-2-liang-shu-xiang-jia-by-guanpengc/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        /*
        // 累加和
        int count = (l1.val + l2.val) % 10;
        // 进位
        int mod = (l1.val + l2.val) / 10;

        ListNode head = new ListNode();

        head.val = count;
        l1 = l1.next;
        l2 = l2.next;

        ListNode pre = head;
        ListNode node = null;
        */

        // 可以创建一个伪头节点
        ListNode fake = new ListNode(0);
        ListNode pre = fake;
        ListNode node = null;
        // 累加和
        int count = 0;
        // 进位
        int ca = 0;

        while (l1 != null || l2 != null) {
            count = ((l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + ca) % 10;
            ca = ((l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + ca) / 10;
            node = new ListNode(count);
            pre.next = node;
            pre = node;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 注意最后一个进位
        if (ca != 0) {
            node = new ListNode(ca);
            pre.next = node;
        }

        return fake.next;
    }
}
