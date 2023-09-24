package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-06-04 21:12
 */
public class Number306 {

    private String num;
    private int n;
    List<List<Integer>> list = new ArrayList<List<Integer>>();

    // 参考：https://leetcode.cn/problems/additive-number/solution/gong-shui-san-xie-hui-su-gao-jing-du-jia-6o6b/
    public boolean isAdditiveNumber(String num) {

        this.num = num;
        n = num.length();
        return dfs(0);
    }

    private boolean dfs(int u) {

        int m = list.size();

        if (u == n)
            return m >= 3;
        // 注意这个位置的 max，当单个元素为 0 时，那么此时只能取这一个位置的数，不能再向后递归
        int max = num.charAt(u) == '0' ? u + 1 : n;
        List<Integer> cur = new ArrayList<Integer>();
        for (int i = u; i < max; i++) {
            cur.add(0, num.charAt(i) - '0');
            if (m < 2 || check(list.get(m - 2), list.get(m - 1), cur)) {
                list.add(cur);
                if (dfs(i + 1))
                    return true;
                // 回溯
                list.remove(list.size() - 1);
            }
        }

        return false;
    }

    private boolean check(List<Integer> a, List<Integer> b, List<Integer> c) {

        List<Integer> ans = new ArrayList<Integer>();
        int t = 0;

        for (int i = 0; i < a.size() || i < b.size(); i++) {
            if (i < a.size())
                t += a.get(i);
            if (i < b.size())
                t += b.get(i);
            ans.add(t % 10);
            // 注意进位
            t /= 10;
        }

        // 注意进位
        if (t > 0)
            ans.add(t);

        boolean ok = c.size() == ans.size();
        for (int i = 0; i < c.size() && ok; i++)
            if (c.get(i) != ans.get(i))
                ok = false;

        return ok;
    }

}
