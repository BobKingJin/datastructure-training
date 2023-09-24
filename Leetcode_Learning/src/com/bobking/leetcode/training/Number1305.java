package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-05-01 10:45
 */
public class Number1305 {

    // 0x3f3f3f3f 的十进制是 1061109567，是 10^9 级别
    // 参考：https://blog.csdn.net/jiange_zh/article/details/50198097
    int INF = 0x3f3f3f3f;

    // 中序遍历 + 归并排序
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        
        List<Integer> res = new ArrayList<Integer>();
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        dfs(root1, l1); 
        dfs(root2, l2);
        int n = l1.size();
        int m = l2.size();
        int i = 0;
        int j = 0;
        
        while (i < n || j < m) {
            int a = i < n ? l1.get(i) : INF;
            int b = j < m ? l2.get(j) : INF;
            if (a <= b) {
                res.add(a);
                i++;
            } else {
                res.add(b);
                j++;
            }
        }

        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> list) {
        
        if (root == null) 
            return ;
        
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
}
