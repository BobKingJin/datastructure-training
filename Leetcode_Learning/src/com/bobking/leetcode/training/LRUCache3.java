package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Date: 2026/8/12 21:47
 * @Author: BobKing
 * @Description:
 */
class LRUCache3 {

    class Node {

        int key, value;
        Node next, prev;
        long expireTime;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        Node(int key, int value, long expireTime) {
            this.key = key;
            this.value = value;
            this.expireTime = expireTime;
        }
    }

    private final int capacity;
    private final Node dummy = new Node(0, 0);
    private final Map<Integer, Node> keyToNode = new HashMap<Integer, Node>();

    public LRUCache3(int capacity) {
        this.capacity = capacity;
        this.dummy.next = dummy;
        this.dummy.prev = dummy;
    }

    public int get(int key) {
        Node node = getNode(key);
        return node != null ? node.value : -1;
    }

    public void put(int key, int value, long expireTime, TimeUnit timeUnit) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            node.expireTime = System.currentTimeMillis() + timeUnit.toMillis(expireTime);
            return;
        }
        node = new Node(key, value, System.currentTimeMillis() + timeUnit.toMillis(expireTime));
        keyToNode.put(key, node);
        pushFront(node);
        if (keyToNode.size() > capacity) {
            Node curNode = dummy.next;
            while (curNode != dummy.prev) {
                if (isExpired(curNode)) {
                    remove(curNode);
                    keyToNode.remove(curNode.key);
                    return;
                }
                curNode = curNode.next;
            }
            remove(curNode);
            keyToNode.remove(curNode.key);
        }
    }

    Node getNode(int key) {
        if (!keyToNode.containsKey(key)) {
            return null;
        }
        Node node = keyToNode.get(key);
        if (isExpired(node)) {
            remove(node);
            keyToNode.remove(key);
            return null;
        }
        remove(node);
        pushFront(node);
        return node;
    }

    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    void pushFront(Node node) {
        node.prev = dummy;
        node.next = dummy.next;
        node.prev.next = node;
        node.next.prev = node;
    }

    boolean isExpired(Node node) {
        return System.currentTimeMillis() > node.expireTime;
    }
}
