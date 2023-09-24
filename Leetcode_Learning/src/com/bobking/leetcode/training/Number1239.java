package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-09-16 21:52
 */
public class Number1239 {

    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    private int get(int cur) {

        if (map.containsKey(cur))
            return map.get(cur);

        int ans = 0;

        for (int i = cur; i > 0; i -= lowbit(i))
            ans++;

        map.put(cur, ans);

        return ans;
    }

    // 二进制表达式中最低位的 1 所对应的值
    private int lowbit(int x) {
        return x & -x;
    }

    int n;
    int ans = Integer.MIN_VALUE;
    int[] hash;

    // 参考：https://leetcode.cn/problems/maximum-length-of-a-concatenated-string-with-unique-characters/solution/gong-shui-san-xie-yi-ti-san-jie-jian-zhi-nfeb/
    public int maxLength1(List<String> arr) {

        n = arr.size();
        HashSet<Integer> set = new HashSet<Integer>();

        for (String s : arr) {
            int val = 0;
            for (char c : s.toCharArray()) {
                int t = (int)(c - 'a');
                // 出现重复字符
                if (((val >> t) & 1) != 0) {
                    val = -1;
                    break;
                }
                val |= (1 << t);
            }
            if (val != -1)
                set.add(val);
        }

        n = set.size();

        if (n == 0)
            return 0;

        hash = new int[n];

        int idx = 0;
        // 维护一个 total，代表后续未经处理的字符串所剩余的 “最大价值” 是多少，从而实现剪枝
        int total = 0;

        for (Integer i : set) {
            hash[idx++] = i;
            total |= i;
        }

        dfs(0, 0, total);
        return ans;
    }

    private void dfs(int u, int cur, int total) {

        if (get(cur | total) <= ans)
            return;

        if (u == n) {
            ans = Math.max(ans, get(cur));
            return;
        }

        // 在原有基础上，选择该数字（如果可以）
        if ((hash[u] & cur) == 0)
            dfs(u + 1, hash[u] | cur, total - (total & hash[u]));

        // 不选择该数字
        dfs(u + 1, cur, total);
    }

    // 参考：https://leetcode.cn/problems/maximum-length-of-a-concatenated-string-with-unique-characters/solution/chuan-lian-zi-fu-chuan-de-zui-da-chang-d-g6gk/
    public int maxLength2(List<String> arr) {

        // 需要计算可行解的长度，至于可行解具体是什么，以及可行解中各个字符的顺序是不用考虑的
        // 因此构成可行解的每个字符串均可以视作一个字符集合，且集合不含重复元素
        // 由于构成可行解的字符串仅包含小写字母，且无重复元素，可以用一个二进制数来表示该字符串的字符集
        // 二进制的第 i 位为 1 表示字符集合中含有第 i 个小写字母，为 0 表示字符集合中不含有第 i 个小写字母
        // 由于包含重复字母的字符串无法参与构成可行解，因此遍历 arr，从中筛选出无重复字母的字符串，将其对应二进制数加入一数组，记作 masks
        // 用 backtrack(pos,mask) 表示递归的函数，其中 pos 表示当前递归到了数组 masks 中的第 pos 个数，mask 表示当前连接得到的字符串对应二进制数为 mask
        // 对于第 pos 个数，有两种方法：选或者不选
        // 如果 mask 和 masks[pos] 无公共元素，则可以选这个数，此时调用 backtrack(pos + 1, masks[pos]) 进行递归
        // 如果不选这个数，那么调用 backtrack(pos + 1, mask) 进行递归
        // 记 masks 的长度为 n，当 pos=n 时，计算 mask 中 1 的个数，即为可行解的长度，用其更新可行解的最长长度

        List<Integer> masks = new ArrayList<Integer>();

        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); ++i) {
                int ch = s.charAt(i) - 'a';
                // 若 mask 已有 ch，则说明 s 含有重复字母，无法构成可行解
                if (((mask >> ch) & 1) != 0) {
                    mask = 0;
                    break;
                }
                // 将 ch 加入 mask 中
                mask |= 1 << ch;
            }
            if (mask > 0)
                masks.add(mask);
        }

        backtrack(masks, 0, 0);
        return ans;
    }

    private void backtrack(List<Integer> masks, int pos, int mask) {

        if (pos == masks.size()) {
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }

        // mask 和 masks[pos] 无公共元素
        if ((mask & masks.get(pos)) == 0)
            backtrack(masks, pos + 1, mask | masks.get(pos));

        backtrack(masks, pos + 1, mask);
    }
}
