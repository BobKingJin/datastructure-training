package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-10-09 10:51
 */
public class Number1743 {

    // 参考：https://leetcode.cn/problems/restore-the-array-from-adjacent-pairs/solution/gong-shui-san-xie-yi-ti-shuang-jie-dan-x-elpx/
    public int[] restoreArray(int[][] adjacentPairs) {

        int m = adjacentPairs.length;
        int n = m + 1;
        Map<Integer, Integer> cnts = new HashMap<Integer, Integer>();
        // 为了方便找到某个数其相邻的数是哪些，还需要再开一个「哈希表」记录相邻关系
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for (int[] ap : adjacentPairs) {
            int a = ap[0];
            int b = ap[1];
            cnts.put(a, cnts.getOrDefault(a, 0) + 1);
            cnts.put(b, cnts.getOrDefault(b, 0) + 1);
            List<Integer> alist = map.getOrDefault(a, new ArrayList<Integer>());
            alist.add(b);
            map.put(a, alist);
            List<Integer> blist = map.getOrDefault(b, new ArrayList<Integer>());
            blist.add(a);
            map.put(b, blist);
        }

        int start = -1;
        for (int i : cnts.keySet()) {
            if (cnts.get(i) == 1) {
                start = i;
                break;
            }
        }

        int[] ans = new int[n];
        ans[0] = start;
        ans[1] = map.get(start).get(0);

        for (int i = 2; i < n; i++) {
            int x = ans[i - 1];
            List<Integer> list = map.get(x);
            for (int j : list) {
                if (j != ans[i - 2])
                    ans[i] = j;
            }
        }

        return ans;
    }

}
