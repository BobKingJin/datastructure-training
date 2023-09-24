package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Number460 {

    private class Node {

        int key;
        int value;
        int freq = 1;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // 参考：https://leetcode-cn.com/problems/lfu-cache/solution/java-13ms-shuang-100-shuang-xiang-lian-biao-duo-ji/
    // 参考：程序猿代码指南P469
    private class LFUCache {

        // 存储缓存的内容
        Map<Integer, Node> cache;
        // 存储每个频次对应的双向链表
        Map<Integer, LinkedHashSet<Node>> freqMap;
        int size;
        int capacity;
        // 存储当前最小频次
        int min;

        public LFUCache(int capacity) {
            cache = new HashMap<Integer, Node>(capacity);
            freqMap = new HashMap<Integer, LinkedHashSet<Node>>();
            this.capacity = capacity;
        }

        public int get(int key) {

            Node node = cache.get(key);
            if (node == null)
                return -1;

            freqInc(node);
            return node.value;
        }

        public void put(int key, int value) {

            if (capacity == 0)
                return;

            Node node = cache.get(key);
            if (node != null) {
                node.value = value;
                freqInc(node);
            } else {
                if (size == capacity) {
                    Node deadNode = removeNode();
                    cache.remove(deadNode.key);
                    size--;
                }
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                addNode(newNode);
                size++;
            }
        }

        public void freqInc(Node node) {

            // 从原 freq 对应的链表里移除, 并更新 min
            int freq = node.freq;
            LinkedHashSet<Node> set = freqMap.get(freq);
            set.remove(node);
            if (freq == min && set.size() == 0)
                min = freq + 1;

            // 加入新 freq 对应的链表
            node.freq++;
            LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
            if (newSet == null) {
                newSet = new LinkedHashSet<Node>();
                freqMap.put(freq + 1, newSet);
            }
            newSet.add(node);
        }

        public void addNode(Node node) {

            LinkedHashSet<Node> set = freqMap.get(1);
            if (set == null) {
                set = new LinkedHashSet<Node>();
                freqMap.put(1, set);
            }
            set.add(node);
            min = 1;
        }

        public Node removeNode() {

            LinkedHashSet<Node> set = freqMap.get(min);
            Node deadNode = set.iterator().next();
            set.remove(deadNode);
            return deadNode;
        }
    }



}
