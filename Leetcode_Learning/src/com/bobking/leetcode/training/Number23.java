package com.bobking.leetcode.training;

import java.util.PriorityQueue;

public class Number23 {

    // 合并两个有序链表可参考：程序猿代码指南P88
    // 参考：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
    public ListNode mergeKLists1(ListNode[] lists) {

        if (lists == null || lists.length == 0)
            return null;

        ListNode res = null;
        for (int i = 0; i < lists.length; i++)
            // 两两进行合并
            res = mergeTwoLists(res, lists[i]);

        return res;
    }

    private ListNode mergeTwoLists(ListNode a, ListNode b) {

        if (a == null || b == null)
            return a != null ? a : b;

        // 创建一个伪头节点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode aPtr = a;
        ListNode bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }

        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    // 参考：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
    public ListNode mergeKLists2(ListNode[] lists) {

        if (lists == null || lists.length == 0)
            return null;

        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {

        if (l == r)
            return lists[l];

        if (l > r)
            return null;

        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    private class Status implements Comparable<Status> {

        private int val;
        private ListNode ptr;

        public Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    // 按照从小到大的顺序排列
    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    // 参考：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
    // bfs
    public ListNode mergeKLists3(ListNode[] lists) {

        if (lists == null || lists.length == 0)
            return null;

        for (ListNode node : lists) {
            if (node != null)
                queue.offer(new Status(node.val, node));
        }

        // 维护当前每个链表没有被合并的元素的最前面一个，k个链表就最多有 k个满足这样条件的元素
        // 每次在这些元素里面选取 val 属性最小的元素合并到答案中
        // 在选取最小元素的时候，可以用优先队列来优化这个过程

        // 创建了一个伪头节点
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null)
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
        }

        return head.next;
    }

}
