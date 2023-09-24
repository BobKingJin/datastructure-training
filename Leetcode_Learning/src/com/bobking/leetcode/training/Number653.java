package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-11-03 11:34
 */
public class Number653 {

    Set<Integer> set = new HashSet<Integer>();

    // 参考：https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/solution/by-ac_oier-zr4o/
    public boolean findTarget1(TreeNode root, int k) {

        if (root == null)
            return false;

        if (set.contains(k - root.val))
            return true;

        set.add(root.val);
        return findTarget1(root.left, k) || findTarget1(root.right, k);
    }

    // 参考：https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/solution/by-ac_oier-zr4o/
    public boolean findTarget2(TreeNode root, int k) {

        // 起始先让 BST 的最左链和最右链完全入栈，此时栈顶元素为 BST 中的最小值和最大值，分别使用 l 和 r 充当指针代指
        // 根据两指针指向的节点值之和与 k 的大小关系来指导如何让 l 和 r 移动
        // l 的移动过程其实就是找下一个比 l.val 更大的值，而 r 的移动过程其实就是找下一个比 r.val 更小的值

        Deque<TreeNode> ld = new ArrayDeque<TreeNode>();
        Deque<TreeNode> rd = new ArrayDeque<TreeNode>();
        TreeNode temp = root;
        while (temp != null) {
            ld.addLast(temp);
            temp = temp.left;
        }
        temp = root;
        while (temp != null) {
            rd.addLast(temp);
            temp = temp.right;
        }

        TreeNode l = ld.peekLast();
        TreeNode r = rd.peekLast();
        while (l.val < r.val) {
            int t = l.val + r.val;
            if (t == k)
                return true;
            if (t < k) {
                l = getNext(ld, true);
            } else {
                r = getNext(rd, false);
            }
        }
        return false;
    }

    private TreeNode getNext(Deque<TreeNode> d, boolean isLeft) {
        TreeNode node = isLeft ? d.pollLast().right : d.pollLast().left;
        while (node != null) {
            d.addLast(node);
            node = isLeft ? node.left : node.right;
        }
        return d.peekLast();
    }

}
