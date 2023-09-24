package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BobKing
 * @create 2023-05-30 8:10
 */
public class Number1110 {

    Set<Integer> toDelete;

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        toDelete = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        List<TreeNode> ans = new ArrayList<TreeNode>();
        slove(root, ans, false);
        return ans;
    }

    private boolean slove(TreeNode root, List<TreeNode> ans, boolean parentExists) {

        boolean del = false;

        if (root == null)
            return del;

        del = toDelete.contains(root.val);

        if (slove(root.left, ans, !del))
            root.left = null;

        if (slove(root.right, ans, !del))
            root.right = null;

        if (!del && !parentExists)
            ans.add(root);

        return del;
    }
}
