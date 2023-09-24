package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-05-11 9:13
 */
public class Number1016 {

    // 参考：https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/solution/san-chong-suan-fa-cong-bao-li-dao-you-hu-nmtq/
    public boolean queryString1(String s, int n) {

        for (int i = 1; i <= n; i++) {
            if (!s.contains(Integer.toBinaryString(i)))
                return false;
        }

        return true;
    }

    // 参考：https://leetcode.cn/problems/binary-string-with-substrings-representing-1-to-n/solution/san-chong-suan-fa-cong-bao-li-dao-you-hu-nmtq/
    public boolean queryString2(String s, int n) {

        // 把 s 的子串都转成二进制数，如果数字在 [1, n] 内，就保存到一个哈希表中。如果哈希表的大小最终为 n
        // 就说明 [1, n] 的二进制都在 s 里面

        HashSet<Integer> seen = new HashSet<Integer>();
        char[] ch = s.toCharArray();

        for (int i = 0, m = ch.length; i < m; ++i) {

            int x = ch[i] - '0';

            // 二进制数从 1 开始
            if (x == 0)
                continue;

            for (int j = i + 1; x <= n; j++) {
                seen.add(x);
                if (j == m)
                    break;
                // 子串 [i, j] 的二进制数
                x = (x << 1) | (ch[j] - '0');
            }
        }
        return seen.size() == n;
    }
}
