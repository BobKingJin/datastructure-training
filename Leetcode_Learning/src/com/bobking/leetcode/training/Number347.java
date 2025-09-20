package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Number347 {

    // 参考：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/leetcode-di-347-hao-wen-ti-qian-k-ge-gao-pin-yuan-/
    public List<Integer> topKFrequent1(int[] nums, int k) {

        List<Integer> res = new ArrayList<Integer>();

        if (nums == null || nums.length == 0 || k > nums.length) {
            return res;
        }

        // 用 map 记录数组中的数出现的次数
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        // 记住桶的长度为 nums.length + 1，因为 0 这个角标不会利用到
        // 记住这个桶的实现是不能用数组来实现的，因为可能会有出现次数相同的数
        // 例如：若 5 和 8 同时出现 4 次，因此这里用 list 数组来作为桶的实现
        ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (Integer key : map.keySet()) {
            int frequnce = map.get(key);
            if (buckets[frequnce] == null) {
                buckets[frequnce] = new ArrayList<Integer>();
            }
            buckets[frequnce].add(key);
        }
        // 例如：list[6] = {8, 2}，那么说明 8 和 2 出现了 6 次
        // 从后往前
        for (int index = buckets.length - 1; res.size() < k; index--) {
            // 说明index这个数没有出现过
            if (buckets[index] == null) {
                continue;
            }
            res.addAll(buckets[index]);
        }

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/top-k-frequent-elements/solution/leetcode-di-347-hao-wen-ti-qian-k-ge-gao-pin-yuan-/
    public List<Integer> topKFrequent2(int[] nums, int k) {

        List<Integer> res = new ArrayList<Integer>();

        if (nums == null || nums.length == 0 || k > nums.length) {
            return res;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历 map，用小根堆保存频率最大的 k 个元素
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });

        for (Integer key : map.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else if (map.get(key) > map.get(queue.peek())) {
                queue.remove();
                queue.add(key);
            }
        }

        // 取出最小堆中的元素
        while (!queue.isEmpty()) {
            res.add(queue.remove());
        }

        return res;
    }
}
