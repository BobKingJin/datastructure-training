package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-04-24 8:13
 */
public class Number1163 {

    // 参考：https://leetcode.cn/problems/last-substring-in-lexicographical-order/solution/c-shuang-zhi-zhen-ji-lu-zi-dian-xu-zui-d-ovwd/
    // 参考：https://leetcode.cn/problems/last-substring-in-lexicographical-order/solution/an-zi-dian-xu-pai-zai-zui-hou-de-zi-chua-31yl/
    public String lastSubstring(String s) {

        // 使用指针 i 指向已知的最大后缀子字符串
        // j 指向待比较的后缀子字符串，初始时有 i = 0，j = 1

        int i = 0;
        int j = 1;
        int n = s.length();

        while (j < n) {

            int k = 0;

            while (j + k < n && s.charAt(i + k) == s.charAt(j + k))
                k++;

            if (j + k < n && s.charAt(i + k) < s.charAt(j + k)) {
                int t = i;
                i = j;
                j = Math.max(j + 1, t + k + 1);
            } else {
                j = j + k + 1;
            }
        }
        return s.substring(i);
    }
}
