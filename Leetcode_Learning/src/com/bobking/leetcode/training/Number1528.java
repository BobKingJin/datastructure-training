package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-13 7:15
 */
public class Number1528 {

    public String restoreString1(String s, int[] indices) {

        int length = s.length();
        char[] res = new char[length];

        for (int i = 0; i < length; i++)
            res[indices[i]] = s.charAt(i);

        return new String(res);
    }

    // 参考: https://leetcode.cn/problems/shuffle-string/solutions/371333/zhong-xin-pai-lie-zi-fu-chuan-by-leetcode-solution/
    public String restoreString2(String s, int[] indices) {

        int length = s.length();
        char[] chars = s.toCharArray();

        for (int i = 0; i < length; i++) {
            if (indices[i] != i) {
                char ch = chars[i];
                int idx = indices[i];
                while (idx != i) {
                    // 使用 swap 函数, 在覆写 s[idx] 之前, 先将其原始值赋给变量 ch
                    swap(s.charAt(idx), ch);
                    // 将封闭路径中的 indices 数组的值设置成下标自身
                    swap(indices[idx], idx);
                }
                // 退出循环后，还要再覆写起点处的字符
                chars[i] = ch;
                indices[i] = i;
            }
        }
        return s;
    }

    private void swap(int i1, int i2) {
        int temp = i1;
        i1 = i2;
        i2 = temp;
    }
}
