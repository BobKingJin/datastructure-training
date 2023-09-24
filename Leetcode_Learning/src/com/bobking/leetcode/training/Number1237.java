package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-02-18 12:13
 */
public class Number1237 {

    interface CustomFunction {
         public int f(int x, int y);
    }

    // 参考：https://leetcode.cn/problems/find-positive-integer-solution-for-a-given-equation/solution/xiang-xiang-shuang-zhi-zhen-yi-ge-shi-pi-nr4y/
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        int x = 1;
        int y = 1000;

        while (x <= 1000 && y > 0) {
            int res = customfunction.f(x, y);
            if (res < z) {
                ++x;
            } else if (res > z) {
                --y;
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(x++);
                list.add(y--);
                ans.add(list);
            }
        }
        return ans;
    }
}
