package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-05-29 9:55
 */
public class Number216 {

    // 参考：https://leetcode.cn/problems/combination-sum-iii/solution/hui-su-jian-zhi-by-liweiwei1419/
    public List<List<Integer>> combinationSum3_1(int k, int n) {

        // ******** 测试用例中 2 <= k <= 9 ********

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        // 根据官方对 Stack 的使用建议，这里将 Deque 对象当做 stack 使用
        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs1(k, n, 1, path, res);
        return res;
    }

    private void dfs1(int k, int residue, int start, Deque<Integer> path, List<List<Integer>> res) {

        // 这一步判断可以放到上一层，减少递归深度
        if (residue < 0)
            return;

        if (k == 0) {
            if (residue == 0) {
                res.add(new ArrayList<Integer>(path));
                return;
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            path.addLast(i);
            dfs1(k - 1, residue - i, i + 1, path, res);
            // 回溯
            path.removeLast();
        }
    }

    // 参考：https://leetcode.cn/problems/combination-sum-iii/solution/hui-su-jian-zhi-by-liweiwei1419/
    public List<List<Integer>> combinationSum3_2(int k, int n) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        // 一开始做一些特殊判断
        if (k <= 0 || n <= 0 || k > n)
            return res;

        // 寻找 n 的上限：[9, 8, ... , (9 - k + 1)]，它们的和为 (19 - k) * k / 2
        // 比上限还大，就不用搜索了
        if (n > (19 - k) * k / 2)
            return res;

        // 根据官方对 Stack 的使用建议，这里将 Deque 对象当做 stack 使用
        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs2(k, n, 1, path, res);
        return res;
    }

    private void dfs2(int k, int residue, int start, Deque<Integer> path, List<List<Integer>> res) {

        // 剪枝：[start, 9] 这个区间里的数都不够 k 个，不用继续往下搜索
        if (10 - start < k)
            return;

        if (k == 0) {
            if (residue == 0) {
                res.add(new ArrayList<Integer>(path));
                return;
            }
        }

        // 枚举起点值 [..., 7, 8, 9]
        // 找 3 个数，起点最多到 7
        // 找 2 个数，起点最多到 8
        // 规律是，起点上界 + k = 10，故起点上界 = 10 - k
        for (int i = start; i <= 10 - k; i++) {

           // if ((2 * i + k - 1) * k / 2 > residue) {
           //     break;
           // }

            // 剪枝
            if (residue - i < 0)
                break;

            path.addLast(i);
            dfs2(k - 1, residue - i, i + 1, path, res);
            // 回溯
            path.removeLast();
        }
    }
}
