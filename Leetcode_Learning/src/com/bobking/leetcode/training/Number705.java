package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-30 12:08
 */
public class Number705 {

    // 参考：https://leetcode.cn/problems/design-hashset/solution/yi-ti-san-jie-jian-dan-shu-zu-lian-biao-nj3dg/
    class MyHashSet1 {

        boolean[] nodes = new boolean[1000009];

        public MyHashSet1() {

        }

        public void add(int key) {
            nodes[key] = true;
        }

        public void remove(int key) {
            nodes[key] = false;
        }

        public boolean contains(int key) {
            return nodes[key];
        }
    }

    // 参考：https://leetcode.cn/problems/design-hashset/solution/yi-ti-san-jie-jian-dan-shu-zu-lian-biao-nj3dg/
    class MyHashSet2 {

        // 由于使用的是「链表」，这个值可以取得很小
        Node[] nodes = new Node[10009];

        public void add(int key) {
            // 根据 key 获取哈希桶的位置
            int idx = getIndex(key);
            // 判断链表中是否已经存在
            Node loc = nodes[idx];
            Node tmp = loc;
            if (loc != null) {
                Node prev = null;
                while (tmp != null) {
                    if (tmp.key == key) {
                        return;
                    }
                    prev = tmp;
                    tmp = tmp.next;
                }
                tmp = prev;
            }
            Node node = new Node(key);

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

        public boolean contains(int key) {
            int idx = getIndex(key);
            Node loc = nodes[idx];
            if (loc != null) {
                while (loc != null) {
                    if (loc.key == key) {
                        return true;
                    }
                    loc = loc.next;
                }
            }
            return false;
        }

        class Node {
            private int key;
            private Node next;
            private Node(int key) {
                this.key = key;
            }
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

    // 参考：https://leetcode.cn/problems/design-hashset/solution/yi-ti-san-jie-jian-dan-shu-zu-lian-biao-nj3dg/
    class MyHashSet3 {

        int[] bs = new int[40000];

        public void add(int key) {
            int bucketIdx = key / 32;
            int bitIdx = key % 32;
            setVal(bucketIdx, bitIdx, true);
        }

        public void remove(int key) {
            int bucketIdx = key / 32;
            int bitIdx = key % 32;
            setVal(bucketIdx, bitIdx, false);
        }

        public boolean contains(int key) {
            int bucketIdx = key / 32;
            int bitIdx = key % 32;
            return getVal(bucketIdx, bitIdx);
        }

        private void setVal(int bucket, int loc, boolean val) {
            if (val) {
                int u = bs[bucket] | (1 << loc);
                bs[bucket] = u;
            } else {
                int u = bs[bucket] & ~(1 << loc);
                bs[bucket] = u;
            }
        }

        private boolean getVal(int bucket, int loc) {
            int u = (bs[bucket] >> loc) & 1;
            return u == 1;
        }
    }

}
