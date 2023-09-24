package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-04 10:32
 */
public class Number2265 {

    int cnt;

    public int averageOfSubtree1(TreeNode root) {
        dfs1(root);
        return cnt;
    }

    private int[] dfs1(TreeNode root) {

        if (root == null)
            return new int[]{0, 0};
        // 后序遍历
        int[] l = dfs1(root.left);
        int[] r = dfs1(root.right);
        int sum = l[0] + r[0] + root.val;
        int nodeNum = l[1] + r[1] + 1;
        int avg = sum / nodeNum;

        if (avg == root.val)
            cnt++;

        return new int[]{sum, nodeNum};
    }

    int res = 0;
    int count = 0;

    public int averageOfSubtree2(TreeNode root) {
        dfs2(root);
        return res;
    }

    private int dfs2(TreeNode root) {

        if (root == null)
            return 0;

        // currCount 用于记录到当前节点为止，上层节点共有多少
        int currCount = count;
        count++;
        int val = dfs2(root.left) + dfs2(root.right) + root.val;

        if (root.val == val / (count - currCount))
            res++;

        return val;
    }
}
