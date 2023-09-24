package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-29 10:13
 */
public class Number2024 {

    String s;
    int n;
    int m;
    
    // 求一个包含 'F' 或者 'T' 的个数不超过 k 的最大长度窗口
    // 参考：https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/solution/by-ac_oier-2rii/
    public int maxConsecutivereswers(String answerKey, int k) {

        s = answerKey;
        n = s.length();
        m = k;
        return Math.max(getCnt('T'), getCnt('F'));
    }

    // 使用 j 和 i 分别代表窗口的左右端点，cnt 为区间 [j, i] 中的字符 c 的数量
    // 每次右端点 i 移动时，若满足 s[i] = c，让 cnt 自增
    // 当 cnt > k 时，使左端点 j 往右移动，同时更新 cnt，直到 [j, i] 区间恢复合法性（包含字符 c 的数量 cnt 不超过 k 个）
    private int getCnt(char c) {
        
        int res = 0;

        for (int i = 0, j = 0, cnt = 0; i < n; i++) {

            if (s.charAt(i) == c)
                cnt++;

            while (cnt > m) {
                if (s.charAt(j) == c)
                    cnt--;
                j++;
            }

            res = Math.max(res, i - j + 1);
        }

        return res;
    }
}
