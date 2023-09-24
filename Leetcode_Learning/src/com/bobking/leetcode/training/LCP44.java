package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-12-11 10:59
 */
public class LCP44 {

    public int numColor(TreeNode root) {
        Set<Integer> list = new HashSet<Integer>();
        getNum(root, list);
        return list.size();
    }

    private void getNum(TreeNode root, Set<Integer> set) {

        if (root == null)
            return;

        set.add(root.val);
        getNum(root.left, set);
        getNum(root.right, set);
    }
}
