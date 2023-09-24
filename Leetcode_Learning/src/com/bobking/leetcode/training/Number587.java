package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2022-04-23 10:38
 */
public class Number587 {

    // 向量相减
    private int[] subtraction(int[] a, int[] b) { 
        return new int[]{a[0] - b[0], a[1] - b[1]};
    }

    // 叉乘
    private double cross(int[] a, int[] b) { 
        return a[0] * b[1] - a[1] * b[0];
    }

    // 向量 ab 转为 向量 ac 过程中扫过的面积
    private double getArea(int[] a, int[] b, int[] c) { 
        return cross(subtraction(b, a), subtraction(c, a));
    }

    // 参考：https://leetcode-cn.com/problems/erect-the-fence/solution/by-ac_oier-4xuu/
    public int[][] outerTrees(int[][] trees) {
        
        Arrays.sort(trees, (a, b)->{
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });
        
        int n = trees.length;
        int tp = 0;
        // 为了方便取得栈顶的前两位元素，使用数组实现栈，stk 代表栈容器，tp 代表栈顶元素下标
        int[] stk = new int[n + 10];
        boolean[] vis = new boolean[n + 10];
        // 不标记起点
        stk[++tp] = 0;
        for (int i = 1; i < n; i++) {
            int[] c = trees[i];
            while (tp >= 2) {
                int[] a = trees[stk[tp - 1]];
                int[] b = trees[stk[tp]];
                if (getArea(a, b, c) < 0) {
                    vis[stk[tp--]] = false;
                } else {
                    break;
                }
            }
            stk[++tp] = i;
            vis[i] = true;
        }
        
        int size = tp;
        for (int i = n - 1; i >= 0; i--) {
            if (vis[i]) 
                continue;
            int[] c = trees[i];
            while (tp > size) {
                int[] a = trees[stk[tp - 1]];
                int[] b = trees[stk[tp]];
                if (getArea(a, b, c) < 0) {
                    // 非必须
                    // vis[stk[tp--]] = false;
                    tp--;
                } else {
                    break;
                }
            }
            stk[++tp] = i;
            // 非必须
            // vis[i] = true;
        }
        
        int[][] res = new int[tp - 1][2];
        for (int i = 1; i < tp; i++) 
            res[i - 1] = trees[stk[i]];

        return res;
    }
    
}
