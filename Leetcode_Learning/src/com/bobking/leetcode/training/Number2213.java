package com.bobking.leetcode.training;

public class Number2213 {

    char[] cs;

    // 参考：https://leetcode.cn/problems/longest-substring-of-one-repeating-character/solution/by-endlesscheng-qpbw/
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {

        // 合并两个子区间时，如果前一个区间（记作 a）的末尾字符等于后一个区间（记作 b）的第一个字符，则可以合并这两个区间
        // 如果 a 的 suf 等于 a 的长度，那么就可以把 b 的 pre 加到 a 的 pre 上
        // 如果 b 的 pre 等于 b 的长度，那么就可以把 a 的 suf 加到 b 的 suf 上
        // a.suf + b.pre 可以考虑成为合并后的区间的 max

        cs = s.toCharArray();
        char[] qcs = queryCharacters.toCharArray();
        int n = cs.length;
        Node root = build(0, n - 1);
        int[] ans = new int[qcs.length];

        for (int i = 0; i < qcs.length; i++) {
            update(root, queryIndices[i], qcs[i]);
            ans[i] = root.max;
        }

        return ans;
    }

    class Node {

        int left;
        int right;
        int max;
        // 前缀最长连续字符个数 pre
        int pre;
        // 后缀最长连续字符个数 suf
        int suf;
        Node leftNode;
        Node rightNode;

        public Node(int l, int r) {
            this.left = l;
            this.right = r;
            this.max = 1;
            this.pre = 1;
            this.suf = 1;
        }
    }

    private Node build(int lo, int hi) {

        if (lo == hi)
            return new Node(lo, hi);

        Node node = new Node(lo, hi);
        int mid = lo + (hi - lo) / 2;
        node.leftNode = build(lo, mid);
        node.rightNode = build(mid + 1, hi);
        merge(node);
        return node;
    }

    private void merge(Node node) {
        if (node.leftNode != null) {
            Node leftNode = node.leftNode;
            Node rightNode = node.rightNode;
            node.max = Math.max(leftNode.max, rightNode.max);
            node.pre = leftNode.pre;
            node.suf = rightNode.suf;
            if (cs[leftNode.right] == cs[rightNode.left]) {
                node.max = Math.max(node.max, leftNode.suf + rightNode.pre);
                if (leftNode.pre == leftNode.right - leftNode.left + 1)
                    node.pre = leftNode.pre + rightNode.pre;
                if (rightNode.suf == rightNode.right - rightNode.left + 1)
                    node.suf = leftNode.suf + rightNode.suf;
            }
        }
    }

    private void update(Node node, int idx, char val) {

        if (node.left > idx || node.right < idx)
            return;

        if (node.left == idx && node.right == idx) {
            cs[idx] = val;
            return;
        }
        update(node.leftNode, idx, val);
        update(node.rightNode, idx, val);
        merge(node);
    }
}
