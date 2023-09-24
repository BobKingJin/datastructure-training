package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-25 12:13
 */
public class SegmentTree {

    // 代码实现参考：https://www.jianshu.com/p/91f2c503e62f
    // 线段树定义参考：https://zhuanlan.zhihu.com/p/34150142
    class SegmentTreeNode {

        int start;
        int end;
        int max;
        SegmentTreeNode left;
        SegmentTreeNode right;

        public SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
            this.left = null;
            this.right = null;
        }
    }

    public SegmentTreeNode build(int[] A) {
        return buildhelper(0, A.length - 1, A);
    }

    private SegmentTreeNode buildhelper(int left, int right, int[] A){

        if(left > right)
            return null;

        // 根据节点区间的左边界的序列值为节点赋初值
        SegmentTreeNode root = new SegmentTreeNode(left, right, A[left]);

        if(left == right)
            // 如果左边界和右边界相等,节点左边界的序列值就是线段树节点的接节点值
            return root;
        // 划分当前区间的左右区间
        int mid = (left + right) / 2;
        root.left = buildhelper(left, mid, A);
        root.right = buildhelper(mid + 1, right, A);
        // 根据节点区间的左右区间的节点值得到当前节点的节点值
        root.max = Math.max(root.left.max, root.right.max);
        return root;
    }

    // 单点更新的代码及注释
    public void modify(SegmentTreeNode root, int index, int value) {

        // 找到被改动的叶子节点
        if(root.start == root.end && root.start == index) {
            root.max = value;
            return;
        }

        int mid = (root.start + root.end) / 2;
        if(index <= mid){
            // 如果index在当前节点的左边
            modify(root.left, index, value);
            // 可能对当前节点的影响
            root.max = Math.max(root.right.max, root.left.max);
        } else {    // 如果index在当前节点的右边
            modify(root.right, index, value);
            // 可能对当前节点的影响
            root.max = Math.max(root.left.max, root.right.max);
        }
        return;
    }

    // 区间查询的代码及注释
    public int query(SegmentTreeNode root, int start, int end) {

        if (start <= root.start && root.end <= end)
            // 如果查询区间在当前节点的区间之内,直接输出结果
            return root.max;

        int mid = (root.start + root.end) / 2;
        int ans = Integer.MIN_VALUE;

        if (mid >= start)
            // 如果查询区间和左边节点区间有交集,则寻找查询区间在左边区间上的最大值
            ans = Math.max(ans, query(root.left, start, end));

        if (mid + 1 <= end)
            // 如果查询区间和右边节点区间有交集,则寻找查询区间在右边区间上的最大值
            ans = Math.max(ans, query(root.right, start, end));

        return ans;
    }
}
