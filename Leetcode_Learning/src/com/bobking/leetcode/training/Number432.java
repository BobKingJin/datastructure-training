package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Number432 {

    // 参考：https://leetcode.cn/problems/all-oone-data-structure/solution/by-ac_oier-t26d/
    private class AllOne {

        // 双向链表
        private class Node {
            int cnt;
            Set<String> set = new HashSet<String>();
            Node left;
            Node right;

            Node(int cnt) {
                this.cnt = cnt;
            }
        }

        Node hh;
        Node tt;
        // 为了快速知道某个字符串属于哪个 Node，还需要开一个「哈希表」进行定位
        // （以字符串为哈希表的键，字符串所在 Node 作为值）
        // 当定位到字符串对应的 Node 之后则可以利用双向链表的 O(1) 增加/修改/删除
        // 在双向链表中，起始只有两个哨兵节点 hh 和 tt

        Map<String, Node> map = new HashMap<String, Node>();

        public AllOne() {
            hh = new Node(-1000);
            tt = new Node(-1000);
            hh.right = tt;
            tt.left = hh;
        }

        // inc/dec 操作：当对一个字符串 key 进行「增加计数」或「减少计数」时，先在哈希表中看 key 是否存在
        // 若存在：根据其所属的 Node 的计数 cnt 为多少，并结合当前是「增加计数」还是「减少计数」来决定是
        // Node 的「右节点」还是「左节点」，同时检查相邻节点的计数值 cnt 是否为目标值，
        // 对应要检查数值是 cnt + 1 和 cnt - 1
        // 若相邻节点的 cnt 为目标值：即目标节点存在，将 key 从原 Node 的 set 集合中移除，并添加到目标节点的集合中
        // 更新哈希表
        // 若相邻节点的 cnt 不是目标值：则需要创建相应的目标节点，并构建双向链表关系，把 key 存入新创建的目标节点
        // 更新哈希表
        // 若不存在（只能是 inc 操作）：查找是否存在 cnt = 1 的节点（也就是检查 hh.right 节点的计数值）
        // 如果存在 cnt = 1 的目标节点：将 key 添加到目标节点的 set 集合中，更新哈希表
        // 若不存在 cnt = 1 的目标节点：创建相应的节点，并构建双向关系，并构建双向链表关系
        // 把 key 存入新创建的目标节点，更新哈希表
        // getMaxKey/getMinKey 操作：分别从 tt.left 和 hh.right 中尝试查找
        // 如果存在非哨兵节点，则从节点的 set 集合中取任意元素进行返回，否则返回空串
        // 最后，为了确保 getMaxKey/getMinKey 操作能够严格 O(1)，在进行 inc/dec 操作时需要对一些 set 容量为 0 的节
        // 点进行释放，即解除其所在双向链表的关系

        private void clear(Node node) {
            if (node.set.size() == 0) {
                node.left.right = node.right;
                node.right.left = node.left;
            }
        }

        public void inc(String key) {

            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.set.remove(key);
                int cnt = node.cnt;
                Node next = null;
                if (node.right.cnt == cnt + 1) {
                    next = node.right;
                } else {
                    next = new Node(cnt + 1);
                    next.right = node.right;
                    next.left = node;
                    node.right.left = next;
                    node.right = next;
                }
                next.set.add(key);
                map.put(key, next);
                clear(node);
            } else {
                Node node = null;
                if (hh.right.cnt == 1) {
                    node = hh.right;
                } else {
                    node = new Node(1);
                    node.right = hh.right;
                    node.left = hh;
                    hh.right.left = node;
                    hh.right = node;
                }
                node.set.add(key);
                map.put(key, node);
            }
        }

        public void dec(String key) {

            Node node = map.get(key);
            node.set.remove(key);
            int cnt = node.cnt;
            if (cnt == 1) {
                map.remove(key);
            } else {
                Node prev = null;
                if (node.left.cnt == cnt - 1) {
                    prev = node.left;
                } else {
                    prev = new Node(cnt - 1);
                    prev.right = node;
                    prev.left = node.left;
                    node.left.right = prev;
                    node.left = prev;
                }
                prev.set.add(key);
                map.put(key, prev);
            }
            clear(node);
        }

        public String getMaxKey() {
            Node node = tt.left;
            for (String str : node.set) return str;
            return "";
        }

        public String getMinKey() {
            Node node = hh.right;
            for (String str : node.set) return str;
            return "";
        }
    }
}
