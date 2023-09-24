package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-07-30 11:06
 */
public class Number706 {

    // 参考：https://leetcode.cn/problems/design-hashmap/solution/yi-ti-shuang-jie-jian-dan-shu-zu-lian-bi-yhiw/
    private class MyHashMap1 {

        int INF = Integer.MAX_VALUE;
        int N = 1000009;
        int[] map = new int[N];

        public MyHashMap1() {
            Arrays.fill(map, INF);
        }

        public void put(int key, int value) {
            map[key] = value;
        }

        public int get(int key) {
            return map[key] == INF ? -1 : map[key];
        }

        public void remove(int key) {
            map[key] = INF;
        }
    }

    // 参考：https://leetcode.cn/problems/design-hashmap/solution/yi-ti-shuang-jie-jian-dan-shu-zu-lian-bi-yhiw/
    private class MyHashMap2 {

        private class Node {

            int key;
            int value;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        // 由于使用的是「链表」，这个值可以取得很小
        Node[] nodes = new Node[10009];

        public void put(int key, int value) {

            int idx = getIndex(key);
            // 判断链表中是否已经存在
            Node loc = nodes[idx];
            Node tmp = loc;
            if (loc != null) {
                Node prev = null;
                while (tmp != null) {
                    if (tmp.key == key) {
                        tmp.value = value;
                        return;
                    }
                    prev = tmp;
                    tmp = tmp.next;
                }
                tmp = prev;
            }
            Node node = new Node(key, value);

            // 头插法
            // node.next = loc;
            // nodes[idx] = node;

            // 尾插法
            if (tmp != null) {
                tmp.next = node;
            } else {
                nodes[idx] = node;
            }
        }

        public void remove(int key) {

            int idx = getIndex(key);
            Node loc = nodes[idx];
            if (loc != null) {
                Node prev = null;
                while (loc != null) {
                    if (loc.key == key) {
                        if (prev != null) {
                            prev.next = loc.next;
                        } else {
                            nodes[idx] = loc.next;
                        }
                        return;
                    }
                    prev = loc;
                    loc = loc.next;
                }
            }
        }

        public int get(int key) {

            int idx = getIndex(key);
            Node loc = nodes[idx];
            if (loc != null) {
                while (loc != null) {
                    if (loc.key == key)
                        return loc.value;
                    loc = loc.next;
                }
            }
            return -1;
        }

        private int getIndex(int key) {
            // 因为 nodes 的长度只有 10009，对应的十进制的 10011100011001（总长度为 32 位，其余高位都是 0）
            // 为了让 key 对应的 hash 高位也参与运算，这里对 hashCode 进行右移异或
            // 使得 hashCode 的高位随机性和低位随机性都能体现在低 16 位中
            int hash = Integer.hashCode(key);
            hash ^= (hash >>> 16);
            return hash % nodes.length;
        }
    }

    // 参考：https://leetcode.cn/problems/design-hashmap/solution/yi-ti-shuang-jie-jian-dan-shu-zu-lian-bi-yhiw/
    private class MyHashMap3 {
        class Node {
            int key;
            int value;
            Node next;
            boolean isDeleted;
            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        // 冲突时的偏移量
        int OFFSET = 1;
        Node[] nodes = new Node[10009];

        public void put(int key, int value) {
            int idx = getIndex(key);
            Node node = nodes[idx];
            if (node != null) {
                node.value = value;
                node.isDeleted = false;
            } else {
                node = new Node(key, value);
                nodes[idx] = node;
            }
        }

        public void remove(int key) {
            Node node = nodes[getIndex(key)];
            if (node != null)
                node.isDeleted = true;
        }

        public int get(int key) {
            Node node = nodes[getIndex(key)];
            if (node == null)
                return -1;
            return node.isDeleted ? -1 : node.value;
        }

        // 当 map 中没有 key 的时候，getIndex 总是返回一个空位置
        // 当 map 中包含 key 的时候，getIndex 总是返回 key 所在的位置
        private int getIndex(int key) {
            int hash = Integer.hashCode(key);
            hash ^= (hash >>> 16);
            int n = nodes.length;
            int idx = hash % n;
            while (nodes[idx] != null && nodes[idx].key != key) {
                hash += OFFSET;
                idx = hash % n;
            }
            return idx;
        }
    }
}
