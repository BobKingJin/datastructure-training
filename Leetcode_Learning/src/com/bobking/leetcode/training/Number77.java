package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-09-10 22:58
 */
public class Number77 {

    // 参考：https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
    public List<List<Integer>> combine1(int n, int k) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k <= 0 || n < k)
            return res;

        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs1(n, k, 1, path, res);
        return res;
    }

    private void dfs1(int n, int k, int index, Deque<Integer> path, List<List<Integer>> res) {

        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        // 遍历可能的搜索起点
        // 注意不能回头的，所以每次 i 都是从 index 开始，即 1 2 和 2 1 是一样的
        for (int i = index; i <= n; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs1(n, k, i + 1, path, res);
            // 回溯
            path.removeLast();
        }
    }

    // 参考：https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
    public List<List<Integer>> combine2(int n, int k) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k <= 0 || n < k)
            return res;

        Deque<Integer> path = new ArrayDeque<Integer>();
        dfs2(n, k, 1, path, res);
        return res;
    }

    private void dfs2(int n, int k, int index, Deque<Integer> path, List<List<Integer>> res) {

        if (path.size() == k) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        // 事实上，如果 n = 7, k = 4，从 5 开始搜索就已经没有意义了，这是因为：即使把 5 选上，后面的数只有 6 和 7
        // 一共就 3 个候选数，凑不出 4 个数的组合。因此，搜索起点有上界
        // 剪枝
        for (int i = index; i <= n - (k - path.size()) + 1; i++) {
            path.addLast(i);
            dfs2(n, k, i + 1, path, res);
            // 回溯
            path.removeLast();
        }
    }

    // 参考：https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
    public List<List<Integer>> combine3(int n, int k) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (k <= 0 || n < k)
            return res;

        // 为了防止底层动态数组扩容，初始化的时候传入最大长度
        Deque<Integer> path = new ArrayDeque<Integer>(k);
        dfs3(1, n, k, path, res);
        return res;
    }

    private void dfs3(int index, int n, int k, Deque<Integer> path, List<List<Integer>> res) {

        if (k == 0) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        // 剪枝
        if (index > n - k + 1)
            return;

        // 不选当前考虑的数 index，直接递归到下一层
        dfs3(index + 1, n, k, path, res);

        path.addLast(index);
        dfs3(index + 1, n, k - 1, path, res);
        // 回溯
        path.removeLast();
    }


}
