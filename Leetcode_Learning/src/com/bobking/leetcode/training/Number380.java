package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Number380 {

    // 参考：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/solution/by-ac_oier-tpex/
    private class RandomizedSet {

        int[] nums;
        Random random;
        Map<Integer, Integer> map;
        int index;

        public RandomizedSet() {

            // 申请一个足够大的数组 nums 利用数据范围为 2 * 10^5
            // 并使用变量 index 记录当前使用到哪一位（即下标在 [0, index] 范围内均是存活值）
            nums = new int[200010];
            random = new Random();
            map = new HashMap<Integer, Integer>();
            index = -1;
        }
        
        public boolean insert(int val) {
            
            if (map.containsKey(val)) 
                return false;
            
            nums[++index] = val;
            map.put(val, index);
            return true;
        }

        // 取出其所在 nums 的下标 location，然后将 nums[index] 赋值到 location 位置
        // 并更新 index（含义为将原本处于 location 位置的元素删除）
        // 同时更新原本位于 index 位置的数在哈希表中的值为 location（若 location 与 index 相等，说明删除的是最后一个元素，这一步可跳过）
        public boolean remove(int val) {
            
            if (!map.containsKey(val)) 
                return false;
            
            int location = map.remove(val);
            
            if (location != index) 
                map.put(nums[index], location);
            
            nums[location] = nums[index--];
            
            return true;
        }
        
        public int getRandom() {
            return nums[random.nextInt(index + 1)];
        }
    }
}
