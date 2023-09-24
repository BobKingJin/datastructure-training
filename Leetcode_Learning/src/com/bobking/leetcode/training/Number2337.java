package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-09-20 9:01
 */
public class Number2337 {

    public boolean canChange(String start, String target) {

        // 替换操作实际上是将 L 往左移动（L 左边为 _ 时才能移动），R 往右移动（R 右边是 _ 时才能移动）
        // 但 L 无法穿过 R，所以如果去掉 start 和 target 中的所有 _，剩下的字符应该是相同的，否则返回 false
        // 使用双指针 i 和 j 从头到尾遍历 start 和 target:
        // 如果当前字符为 L 且 i < j，那么这个 L 无法向右移动，返回 false
        // 如果当前字符为 R 且 i > j，那么这个 R 无法向左移动，返回 false

        if (!start.replaceAll("_", "").equals(target.replaceAll("_", "")))
            return false;

        int i = 0;
        int j = 0;
        int n = start.length();

        while (true) {

            while (i < n && start.charAt(i) == '_')
                ++i;
            while (j < n && target.charAt(j) == '_')
                ++j;
            if (i == n && j == n)
                return true;
            if (i == n || j == n || start.charAt(i) != target.charAt(j))
                return false;

            if (start.charAt(i) == 'L' && i < j || start.charAt(i) == 'R' && i > j)
                return false;
            ++i;
            ++j;
        }
    }
}
