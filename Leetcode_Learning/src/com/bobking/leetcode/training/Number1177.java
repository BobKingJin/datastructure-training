package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-06-15 9:10
 */
public class Number1177 {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {

        List<Boolean> res = new ArrayList<Boolean>();
        int cur = 0;
        int[] states = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            // 比如 a 就是 00...001 , b 就是00...010
            // 与 cur 异或，可认为是去除重复的字符，添加尚未存在的字符
            // 比如 之前是 a, 现在又来了一个 a, 则 00...001 ^ 00...001, 结果就会变成 0, 就不含 a 字符了
            // 若之前是a, 现在来了b, 则 00...001 ^ 00...010, 结果就变成了 00...011, 表示含有了 a, b字符
            // 得到的 cur 表示的就是在当前位置之前，削掉所有出现了偶数次的字符后，所余下的字符对应的位向量
            // 比如，余下了a, c, d, 则此时 cur 就是 00...001101
            // 就得到了在字符串每一个字符之前, 包含本身. 共出现了奇数次的字符的情况
            cur ^= (1 << (s.charAt(i) - 'a'));
            states[i] = cur;
        }

        for (int i = 0; i < queries.length; i++) {
            // 若是从零开始, 则取零, 否则, 取当前起始值的前一个的状态
            int ostate = queries[i][0] > 0 ? states[queries[i][0] - 1] : 0;
            // 首尾异或, 就得到了在这个区间内, 出现了奇数次的字符的情况
            int state = ostate ^ states[queries[i][1]];
            int cnt = 0;
            while (state != 0) {
                if ((state & 1) == 1)
                    cnt++;
                state >>= 1;
            }
            res.add(cnt / 2 <= queries[i][2]);
        }
        return res;
    }
}
