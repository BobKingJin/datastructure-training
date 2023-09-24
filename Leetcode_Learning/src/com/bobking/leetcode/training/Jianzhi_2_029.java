package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-18 0:21
 */
public class Jianzhi_2_029 {

    private class Node {

        public int val;
        public Node next;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    // 参考：https://leetcode.cn/problems/4ueAj6/solution/gan-jue-da-jia-xie-de-du-you-dian-fu-za-k1klz/
    public Node insert(Node head, int insertVal) {

        // 在中间能够找到一个节点 cur，满足 cur.val <= val <= cur.next.val，直接插入即可
        // 找不到，则一定是所有的值都比它小或大，其实都会插入到边界跳跃点，即找到 cur
        // 满足 val <= cur.next.val < cur.val(比最小的还小）
        // 或 cur.next.val < cur.val <= val（比最大的还大）
        // 因此其实就三个不等式，cur.val <= val, cur.next.val >= val, cur.next.val >= cur.val，三个式子中满足一个或三个时，cur即为插入点

        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }

        Node cur = head;
        while (cur.next != head) {
            if (cur.next.val < cur.val) {
                if (cur.next.val >= insertVal) {
                    // 最小值
                    // [3, 5, 1] 0 ==> [3, 5, 0, 1]
                    break;
                } else if (cur.val <= insertVal) {
                    // 最大值
                    // [3, 5, 1] 6 ==> [3, 5, 6, 1]
                    break;
                }
            }
            // 中间顺序插入
            // [1, 3, 5] 2 ==> [1, 2, 3, 5]
            if (cur.val <= insertVal && cur.next.val >= insertVal)
                break;

            // 移动指针
            cur = cur.next;
        }
        // 插入新节点
        cur.next = new Node(insertVal, cur.next);
        return head;
    }


}
