package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-07-31 12:24
 */
public class Number443 {

    // 参考：https://leetcode.cn/problems/string-compression/solution/gong-shui-san-xie-shuang-zhi-zhen-yuan-d-bppu/
    public int compress(char[] chars) {

        // 使用两个指针 i 和 j 分别指向「当前处理到的位置」和「答案待插入的位置」
        // i 指针一直往后处理，每次找到字符相同的连续一段 [i, idx)，令长度为 cnt
        // 将当前字符插入到答案，并让 j 指针后移：cs[j++] = cs[i]
        // 检查长度 cnt 是否大于 1，如果大于 1，需要将数字拆分存储
        // 由于简单的实现中，只能从个位开始处理 cnt，因此需要使用 start 和 end 记录下存储数字的部分，再处理完 cnt 后，将 [start, end) 部分进行翻转
        // 并更新 j 指针
        // 更新 i 为 idx，代表循环处理下一字符

        int n = chars.length;
        int i = 0;
        int j = 0;
        while (i < n) {
            int idx = i;
            while (idx < n && chars[idx] == chars[i])
                idx++;
            int cnt = idx - i;
            chars[j++] = chars[i];
            if (cnt > 1) {
                int start = j;
                int end = start;
                while (cnt != 0) {
                    chars[end++] = (char)((cnt % 10) + '0');
                    cnt /= 10;
                }
                reverse(chars, start, end - 1);
                j = end;
            }
            i = idx;
        }
        return j;
    }

    private void reverse(char[] cs, int start, int end) {
        while (start < end) {
            char t = cs[start];
            cs[start] = cs[end];
            cs[end] = t;
            start++;
            end--;
        }
    }

}
