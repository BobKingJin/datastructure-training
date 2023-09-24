package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-13 11:11
 */
public class Number988 {

    // 波浪线的 ascii 码是 126 大于所有字符 ，其实就是 ans 一开始给一个”最大值“
    String ans = "~";

    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return ans;
    }

    private void dfs(TreeNode node, StringBuilder sb) {

        if (node == null)
            return;

        sb.append((char)('a' + node.val));

        if (node.left == null && node.right == null) {
            sb.reverse();
            String S = sb.toString();
            sb.reverse();

            if (S.compareTo(ans) < 0)
                ans = S;
        }

        dfs(node.left, sb);
        dfs(node.right, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
