package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2024/2/24 16:44
 * @Author: BobKing
 * @Description:
 */
public class Number2476 {

    // 参考: https://leetcode.cn/problems/closest-nodes-queries-in-a-binary-search-tree/solutions/2651916/zhong-xu-bian-li-er-fen-cha-zhao-pythonj-4ic0/?envType=daily-question&envId=2024-02-24
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {

        List<Integer> al = new ArrayList<Integer>();
        dfs(root, al);
        Integer[] a = al.stream().toArray(Integer[]::new);
        int n = a.length;

        List<List<Integer>> ans = new ArrayList<List<Integer>>(queries.size());
        for (int q : queries) {
            // 设 j 是大于等于 q = queriesi 的第一个数的下标, 如果不存在则 j = n
            // 对于 maxi:
            //    如果 j < n, 那么 maxi = a[j]
            //    否则 maxi = −1
            // 对于 mini:
            //    如果 j < n 且 a[j] = q, 那么 mini = a[j]
            //    否则如果 j > 0,那么 mini = a[j−1]
            //    否则 mini = −1
            int j = lowerBound(a, q);
            int mx = j == n ? -1 : a[j];
            if (j == n || a[j] != q) // a[j] > q, a[j - 1] < q
                j--;
            int mn = j < 0 ? -1 : a[j];
            ans.add(List.of(mn, mx));
        }
        return ans;
    }

    private void dfs(TreeNode node, List<Integer> a) {

        if (node == null)
            return;

        dfs(node.left, a);
        a.add(node.val);
        dfs(node.right, a);
    }

    private int lowerBound(Integer[] a, int target) {
        // 开区间 (left, right)
        int left = -1;
        int right = a.length;
        while (left + 1 < right) { // 区间不为空
            int mid = (left + right) >>> 1;
            if (a[mid] >= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return right;
    }
}
