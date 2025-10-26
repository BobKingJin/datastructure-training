package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BobKing
 * @create 2021-07-09 21:43
 */
public class Number301 {

    // 参考：https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/bfsjian-dan-er-you-xiang-xi-de-pythonjiang-jie-by-/
    public List<String> removeInvalidParentheses1(String s) {

        if (s == null || s.length() < 1) {
            return null;
        }

        // 当前层的
        Set<String> level = new HashSet<String>();
        level.add(s);

        while (true) {
            // 过滤不合法的
            List<String> valid = level.stream().filter(this::isValid).collect(Collectors.toList());
            if (valid.size() > 0) {
                return valid;
            }
            // 下一层
            Set<String> nextLevel = new HashSet<String>();
            for (String item : level) {
                // 每次移除一个括号
                for (int i = 0; i < item.length(); i++) {
                    if (item.charAt(i) == '(' || item.charAt(i) == ')') {
                        nextLevel.add(item.substring(0, i) + item.substring(i + 1));
                    }
                }
            }
            level = nextLevel;
            // 全部括号都被移除依然不符合，跳出循环
            if (level.size() == 0) {
                return new ArrayList<String>();
            }
        }
    }

    private boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cnt++;
            } else if (c == ')') {
                cnt--;
            }
            // 右边括号比左括号多，不合法
            if (cnt < 0) {
                return false;
            }
        }
        return cnt == 0;
    }

    private int len;
    private char[] charArray;
    private Set<String> validExpressions = new HashSet<String>();

    // 参考：https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/shan-chu-wu-xiao-de-gua-hao-by-leetcode/
    public List<String> removeInvalidParentheses2(String s) {

        if (s == null || s.length() < 1) {
            return null;
        }

        len = s.length();
        charArray = s.toCharArray();

        // 第 1 步：遍历一次，计算多余的左右括号
        int leftRemove = 0;
        int rightRemove = 0;
        for (int i = 0; i < len; i++) {
            if (charArray[i] == '(') {
                leftRemove++;
            } else if (charArray[i] == ')') {
                // 遇到右括号的时候，需要根据已经存在的左括号数量决定
                if (leftRemove == 0) {
                    rightRemove++;
                } else if (leftRemove > 0) {
                    // 关键：一个右括号出现可以抵销之前遇到的左括号
                    leftRemove--;
                }
            }
        }

        // 第 2 步：回溯算法，尝试每一种可能的删除操作
        StringBuilder path = new StringBuilder();
        dfs(0, 0, 0, leftRemove, rightRemove, path);
        return new ArrayList<String>(validExpressions);
    }

    /**
     * leftCount：已经遍历到的左括号的个数 rightCount：已经遍历到的右括号的个数 leftRemove：最少应该删除的左括号的个数
     * rightRemove：最少应该删除的右括号的个数
     */
    private void dfs(int index, int leftCount, int rightCount, int leftRemove, int rightRemove,
        StringBuilder path) {
        if (index == len) {
            if (leftRemove == 0 && rightRemove == 0) {
                validExpressions.add(path.toString());
            }
            return;
        }

        // 两种可能操作：删除当前字符和保留当前字符

        char character = charArray[index];
        // 可能的操作 1：删除当前遍历到的字符
        if (character == '(' && leftRemove > 0) {
            // 由于 leftRemove > 0，并且当前遇到的是左括号，因此可以尝试删除当前遇到的左括号
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        if (character == ')' && rightRemove > 0) {
            // 由于 rightRemove > 0，并且当前遇到的是右括号，因此可以尝试删除当前遇到的右括号
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }
        // 可能的操作 2：保留当前遍历到的字符
        path.append(character);
        if (character != '(' && character != ')') {
            // 如果不是括号，继续深度优先遍历
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (character == '(') {
            // 考虑左括号
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
            // 必须右括号 < 左括号 才合法
        } else if (rightCount < leftCount) {
            // 考虑右括号
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        // 回溯
        path.deleteCharAt(path.length() - 1);
    }

}
