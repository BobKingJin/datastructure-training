package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author BobKing
 * @create 2023-04-20 22:55
 */
public class Number1424 {

    // 参考：https://leetcode.cn/problems/diagonal-traverse-ii/solution/treemapan-dui-jiao-xian-ju-he-zhi-by-zuo-zhou-ren/
    public int[] findDiagonalOrder(List<List<Integer>> nums) {

        int len = 0;
        Map<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        for (int i = 0; i < nums.size(); i++) {
            // 获取最后要返回的数组的长度，即元素个数
            len += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (map.containsKey(i + j)) {
                    map.get(i + j).add(nums.get(i).get(j));
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums.get(i).get(j));
                    map.put(i + j, list);
                }
            }
        }
        int[] ans = new int[len];
        int index = 0;
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int j = list.size() - 1; j >= 0; j--) {
                ans[index] = list.get(j);
                index++;
            }
        }
        return ans;
    }
}
