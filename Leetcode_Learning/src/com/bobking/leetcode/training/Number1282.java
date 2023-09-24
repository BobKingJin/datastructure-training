package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-08-12 23:19
 */
public class Number1282 {

    // 参考：https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/solution/by-ac_oier-z1bg/
    public List<List<Integer>> groupThePeople(int[] groupSizes) {

        // 可以使用「哈希表」将所属组大小相同的下标放到一起
        // 假设组大小为 k 的元素有 m 个，然后再将这 m 个元素按照 k 个一组进行划分即可

        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> list = map.getOrDefault(groupSizes[i], new ArrayList<Integer>());
            list.add(i);
            map.put(groupSizes[i], list);
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (int k : map.keySet()) {
            List<Integer> list = map.get(k);
            List<Integer> cur = new ArrayList<Integer>();
            for (int i = 0; i < list.size(); i++) {
                cur.add(list.get(i));
                if (cur.size() == k) {
                    ans.add(cur);
                    cur = new ArrayList<Integer>();
                }
            }
        }
        return ans;
    }
}
