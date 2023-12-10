package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number17 {

    private String[] map = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    // 参考：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode-solutio/
    // 递归 + 回溯 dfs
    // 对比Number46，全排列
    // 注意此题和Number46的区别，此题是必须按顺序的
    // 例如：digits = "23" 那么号码 2 对应的字母 abc 必须出现在第一位上面，而不能 3 对应的字母 def 出现在第一位
    public List<String> letterCombinations1(String digits) {

        List<String> result = new ArrayList<String>();

        if (digits == null || digits.equals(""))
            return result;

        StringBuilder sb = new StringBuilder("");
        backTrace(digits, 0, sb, result);
        return result;
    }

    private void backTrace(String digits, int index, StringBuilder sb, List<String> result) {

        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            // 注意角标变化, 是不能在同一个数字上面选择多个字母的, 一个数字上面只能选择一个字母
            backTrace(digits, index + 1, sb, result);
            // 回溯
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // bfs
    public List<String> letterCombinations2(String digits) {

        List<String> result = new ArrayList<String>();

        if (digits == null || digits.equals(""))
            return result;

        result.add("");

        // 这个位置注意 for 循环的顺序
        // 先是取 digits 中的每个字符集, 再是 res 中的结果集, 最后才是 字符集 中的每个字母
        for (int i = 0; i < digits.length(); i++) {
            String letters = map[digits.charAt(i) - '0'];
            for (int j = 0; j < result.size(); j++) {
                // 注意：这里提前进行了remove操作, 相当于队列的出队操作
                String tmp = result.remove(0);
                for (int k = 0; k < letters.length(); k++)
                    result.add(tmp + letters.charAt(k));
            }
        }

        return result;
    }

}
