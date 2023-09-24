package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-03-19 10:55
 */
public class Number606 {

    StringBuilder sb = new StringBuilder();

    // 参考：https://leetcode-cn.com/problems/construct-string-from-binary-tree/solution/by-ac_oier-i2sk/
    public String tree2str1(TreeNode root) {
        dfs(root);
        return sb.substring(1, sb.length() - 1);
    }

    // 先序遍历
    // 当以某个节点 x 为根时，其只「有左子树」而「没有右子树」时，右子树的 () 可被忽略
    // 或者「左右子树都没有」时，两者的 () 可被忽略
    // 换句话说：当「有右子树」同时「没有左子树」时，左子树的 () 不能被忽略
    private void dfs(TreeNode root) {

        sb.append("(");
        sb.append(root.val);
        if (root.left != null){
            dfs(root.left);
            // 左节点为 null
        } else if (root.right != null){
            // 当「有右子树」同时「没有左子树」时，左子树的 () 不能被忽略
            sb.append("()");
        }
        if (root.right != null)
            dfs(root.right);
        // 还得把右括号补上
        sb.append(")");
    }

    // 参考：https://leetcode-cn.com/problems/construct-string-from-binary-tree/solution/by-ac_oier-i2sk/
    public String tree2str2(TreeNode root) {

        // 由于当以某个节点 x 为根节点时，其需要在 开始 前序遍历当前子树时添加 (，在 结束 前序遍历时添加 )，因此某个节点需要出入队两次
        // 同时区分是首次出队（开始前序遍历）还是二次出队（结束前序遍历），这需要使用一个 set 来做记录
        StringBuilder sb = new StringBuilder();
        Set<TreeNode> vis = new HashSet<TreeNode>();
        Deque<TreeNode> d = new ArrayDeque<TreeNode>();
        d.addLast(root);
        // 先序遍历
        while (!d.isEmpty()) {
            TreeNode t = d.pollLast();
            // 还得把右括号补上
            if (vis.contains(t)) {
                sb.append(")");
            } else {
                // 节点需要出入队两次
                d.addLast(t);
                sb.append("(");
                sb.append(t.val);
                if (t.right != null)
                    d.addLast(t.right);
                if (t.left != null){
                    d.addLast(t.left);
                } else if (t.right != null){
                    sb.append("()");
                }
                vis.add(t);
            }
        }

        return sb.substring(1, sb.length() - 1);
    }

    // 参考：https://leetcode-cn.com/problems/construct-string-from-binary-tree/solution/by-ac_oier-i2sk/
    public String tree2str3(TreeNode root) {

        StringBuilder sb = new StringBuilder();
        Deque<Object[]> d = new ArrayDeque<>();
        d.addLast(new Object[]{0, root});
        while (!d.isEmpty()) {
            Object[] poll = d.pollLast();
            int loc = (Integer)poll[0];
            TreeNode t = (TreeNode)poll[1];
            if (loc == 0) {
                sb.append("(");
                sb.append(t.val);
                d.addLast(new Object[]{1, t});
            } else if (loc == 1) {
                d.addLast(new Object[]{2, t});
                if (t.right != null)
                    d.addLast(new Object[]{0, t.right});
                if (t.left != null){
                    d.addLast(new Object[]{0, t.left});
                } else if (t.right != null) {
                    sb.append("()");
                }
            } else if (loc == 2) {
                sb.append(")");
            }
        }

        return sb.substring(1, sb.length() - 1);
    }






}
