package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Number22 {

    // 递归
    public List<String> generateParenthesis1(int n) {

        List<String> res = new ArrayList<String>();

        if (n < 1)
            return res;

        generateAll(new char[2 * n], 0, res);
        return res;
    }

    private void generateAll(char[] ch, int index, List<String> res) {

        if (index == ch.length) {
            // 暴力递归尝试所有结果后，一定要检测生成的括号的有效性
            if (checkValidation(ch))
                res.add(new String(ch));
        } else {
            ch[index] = '(';
            generateAll(ch, index + 1, res);
            // 将先前的 index 位置的角标进行覆盖
            ch[index] = ')';
            generateAll(ch, index + 1, res);
        }
    }

    private boolean checkValidation(char[] ch) {

        int count = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                count++;
            } else {
                count--;
                if (count < 0)
                    break;
            }
        }
        return count == 0;
    }

    // 参考：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
    public List<String> generateParenthesis2(int n) {

        List<String> res = new ArrayList<String>();

        if (n == 0)
            return res;

        // 做减法
        dfs("", n, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, List<String> res) {

        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 回溯的本质是去掉当前字符，尝试所有可能，这里先尝试当前字符为 ( 然后尝试当前字符为 ) 这两种可能都尝试了，因此无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left > right)
            return;

        if (left > 0)
            dfs(curStr + "(", left - 1, right, res);

        if (right > 0)
            dfs(curStr + ")", left, right - 1, res);
    }

    // 参考：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
    public List<String> generateParenthesis3(int n) {

        List<String> res = new ArrayList<String>();

        if (n == 0)
            return res;

        // 做加法
        dfs("", 0, 0, n, res);
        return res;
    }

    private void dfs(String curStr, int left, int right, int n, List<String> res) {

        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right)
            return;

        if (left < n)
            dfs(curStr + "(", left + 1, right, n, res);

        if (right < n)
            dfs(curStr + ")", left, right + 1, n, res);
    }

    private class Node {

        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    // 参考：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
    public List<String> generateParenthesis4(int n) {

        List<String> res = new ArrayList<String>();
        if (n == 0)
            return res;
        // 因为左括号和右括号有数量限制，且必须符合左括号数量要小于右括号数量，所以必须重新定义一个数据结构Node
        // 来记录当前左括号和右括号的数量
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0)
                res.add(curNode.res);
            if (curNode.left > 0)
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            // 左括号数量一定要小于右括号数量
            if (curNode.right > 0 && curNode.left < curNode.right)
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
        }

        return res;
    }


}
