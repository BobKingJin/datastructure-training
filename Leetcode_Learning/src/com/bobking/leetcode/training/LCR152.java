package com.bobking.leetcode.training;

import java.util.Stack;

/**
 * @Date: 2024/11/1 11:00
 * @Author: BobKing
 * @Description:
 */
public class LCR152 {

    public boolean verifyTreeOrder1(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] postorder, int i, int j) {
        if (i >= j)
            return true;
        int p = i;
        while (postorder[p] < postorder[j])
            p++;
        int m = p;
        while (postorder[p] > postorder[j])
            p++;
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    // 参考: https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solutions/150225/mian-shi-ti-33-er-cha-sou-suo-shu-de-hou-xu-bian-6/
    public boolean verifyTreeOrder2(int[] postorder) {

        Stack<Integer> stack = new Stack<Integer>();
        int root = Integer.MAX_VALUE;

        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root)
                return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }

}
