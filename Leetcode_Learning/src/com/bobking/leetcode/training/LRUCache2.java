package com.bobking.leetcode.training;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Date: 2026/8/12 21:36
 * @Author: BobKing
 * @Description:
 */
public class LRUCache2 {

    private final int capacity;
    // 内置 LRU
    private final Map<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>();

    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        // 先删除 再 重新 put, 这样相当于 addFirst()
        Integer value = cache.remove(key);
        if (value != null) {
            cache.put(key, value);
            return value;
        }

        return -1;
    }

    public void put(int key, int value) {
        // 删除 key
        if (cache.remove(key) != null) {
            cache.put(key, value);
            return;
        }

        if (cache.size() == capacity) {
            Integer eldestKey = cache.keySet().iterator().next();
            cache.remove(eldestKey);
        }
        cache.put(key, value);
    }

}
