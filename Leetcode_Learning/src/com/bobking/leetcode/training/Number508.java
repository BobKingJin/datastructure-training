package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-06-19 9:49
 */
public class Number508 {

    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private int max = 0;

    public int[] findFrequentTreeSum(TreeNode root) {

        dfs(root);
        List<Integer> list = new ArrayList<Integer>();
        for (int k : map.keySet())
            if (map.get(k) == max)
                list.add(k);

        int n = list.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = list.get(i);

        return ans;
    }

    private int dfs(TreeNode root) {

        if (root == null)
            return 0;

        int cur = root.val + dfs(root.left) + dfs(root.right);
        map.put(cur, map.getOrDefault(cur, 0) + 1);
        max = Math.max(max, map.get(cur));
        return cur;
    }
}
