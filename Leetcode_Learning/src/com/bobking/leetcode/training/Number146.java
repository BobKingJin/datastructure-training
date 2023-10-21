package com.bobking.leetcode.training;

import java.util.HashMap;

public class Number146 {

    // 参考：https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
    // 参考：程序猿代码指南P465
    private class LRUCache {

        private class Node {
            public int key;
            public int val;
            public Node next;
            public Node prev;

            public Node(int k, int v) {
                this.key = k;
                this.val = v;
            }
        }

        class DoubleList {
            private Node head;
            private Node tail;
            private int size;

            public void addFirst(Node node) {
                if (head == null) {
                    head = tail = node;
                } else {
                    Node n = head;
                    n.prev = node;
                    node.next = n;
                    head = node;
                }
                size++;
            }

            public void remove(Node node) {
                if (head == node && tail == node) {
                    head = null;
                    tail = null;
                } else if (tail == node) {
                    node.prev.next = null;
                    tail = node.prev;
                } else if (head == node) {
                    node.next.prev = null;
                    head = node.next;
                } else {
                    node.prev.next = node.next;
                    node.next.prev = node.prev;
                }
                size--;
            }

            public Node removeLast() {
                Node node = tail;
                remove(tail);
                return node;
            }

            public int size() {
                return size;
            }
        }

        private HashMap<Integer, Node> map;
        private DoubleList cache;
        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            this.map = new HashMap<Integer, Node>();
            this.cache = new DoubleList();
        }

        public int get(int key) {

            if (!map.containsKey(key))
                return -1;

            int val = map.get(key).val;
            put(key, val);
            return val;
        }

        public void put(int key, int value) {

            Node x = new Node(key, value);

            if (map.containsKey(key)) {
                cache.remove(map.get(key));
            } else {
                if (cap == cache.size()) {
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
            }

            cache.addFirst(x);
            map.put(key, x);
        }
    }
}
