package com.bobking.leetcode.training;

public class Number707 {

    // 参考：https://leetcode.cn/problems/design-linked-list/solution/she-ji-lian-biao-by-leetcode/
    private class MyLinkedList1 {

        int size;
        ListNode head;
        public MyLinkedList1() {
            this.size = 0;
            this.head = new ListNode(0);
        }

        public int get(int index) {

            if (index < 0 || index >= size)
                return -1;

            ListNode cur = head;

            for(int i = 0; i < index + 1; ++i)
                cur = cur.next;

            return cur.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        public void addAtIndex(int index, int val) {

            if (index > size)
                return;

            if (index < 0)
                index = 0;

            ++size;

            ListNode pred = head;
            for(int i = 0; i < index; ++i)
                pred = pred.next;

            ListNode toAdd = new ListNode(val);

            toAdd.next = pred.next;
            pred.next = toAdd;
        }

        public void deleteAtIndex(int index) {

            if (index < 0 || index >= size)
                return;

            size--;

            ListNode pred = head;
            for(int i = 0; i < index; ++i)
                pred = pred.next;

            pred.next = pred.next.next;
        }
    }

    // 参考：https://leetcode.cn/problems/design-linked-list/solution/she-ji-lian-biao-by-leetcode/
    private class MyLinkedList2 {

        int size;
        DoubleListNode head;
        DoubleListNode tail;

        public MyLinkedList2() {
            this.size = 0;
            this.head = new DoubleListNode(0);
            this.tail = new DoubleListNode(0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int index) {

            if (index < 0 || index >= size)
                return -1;

            DoubleListNode cur = head;
            if (index + 1 < size - index){
                for(int i = 0; i < index + 1; ++i)
                    cur = cur.next;
            } else {
                cur = tail;
                for(int i = 0; i < size - index; ++i)
                    cur = cur.prev;
            }

            return cur.val;
        }

        public void addAtHead(int val) {

            DoubleListNode pre = head;
            DoubleListNode suc = head.next;

            ++size;
            DoubleListNode toAdd = new DoubleListNode(val);
            toAdd.prev = pre;
            toAdd.next = suc;
            pre.next = toAdd;
            suc.prev = toAdd;
        }

        public void addAtTail(int val) {

            DoubleListNode succ = tail;
            DoubleListNode pred = tail.prev;

            ++size;
            DoubleListNode toAdd = new DoubleListNode(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        public void addAtIndex(int index, int val) {

            if (index > size)
                return;

            if (index < 0)
                index = 0;

            DoubleListNode pred;
            DoubleListNode succ;
            if (index < size - index) {
                pred = head;
                for(int i = 0; i < index; ++i)
                    pred = pred.next;
                succ = pred.next;
            } else {
                succ = tail;
                for (int i = 0; i < size - index; ++i)
                    succ = succ.prev;
                pred = succ.prev;
            }

            ++size;
            DoubleListNode toAdd = new DoubleListNode(val);
            toAdd.prev = pred;
            toAdd.next = succ;
            pred.next = toAdd;
            succ.prev = toAdd;
        }

        public void deleteAtIndex(int index) {

            if (index < 0 || index >= size)
                return;

            DoubleListNode pred, succ;
            if (index < size - index) {
                pred = head;
                for(int i = 0; i < index; ++i)
                    pred = pred.next;
                succ = pred.next.next;
            }
            else {
                succ = tail;
                for (int i = 0; i < size - index - 1; ++i)
                    succ = succ.prev;
                pred = succ.prev.prev;
            }

            --size;
            pred.next = succ;
            succ.prev = pred;
        }
    }
}
