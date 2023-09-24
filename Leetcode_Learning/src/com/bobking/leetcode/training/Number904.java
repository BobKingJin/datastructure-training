package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-08-20 22:52
 */
public class Number904 {

    public int totalFruit1(int[] fruits) {

        if (fruits.length == 1 || fruits.length == 2)
            return fruits.length;

        // 左边指针
        int left = 0;
        // 右边指针
        int right = 0;
        // 左边水果
        int ln = fruits[0];
        // 右边水果
        int rn = fruits[0];
        int result = Integer.MIN_VALUE;

        for (; right < fruits.length; right++) {
            // 当 fruits[right] != rn && fruits[right] != ln 时，说明出现了一种新的水果种类，那么可以尝试新的水果种类
            // 将之前的 fruit[left] 舍弃
            if (fruits[right] != rn && fruits[right] != ln) {
                // 左边指针移到右指前一位
                left = right - 1;
                while (left >= 1 && fruits[left - 1] == fruits[left])
                    // 确保左指针和前面不一致，一致需要左指针左移
                    left--;

                ln = fruits[left];
                rn = fruits[right];
            }

            result = Math.max(result, right - left + 1);
        }

        return Math.max(2, result);
    }

    // 参考：https://leetcode.cn/problems/fruit-into-baskets/solution/tao-mo-ban-hua-dong-chuang-kou-qiu-zui-c-pner/
    public int totalFruit2(int[] fruits) {

        if (fruits == null || fruits.length == 0)
            return 0;

        int n = fruits.length;
        // Map<水果种类，出现频次>
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLen = 0;
        int left = 0;

        for (int i = 0; i < n; i++) {
            // 右边界
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);
            // 不符合条件：水果种类大于 2
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0)
                    map.remove(fruits[left]);
                // 左边界
                left++;
            }

            maxLen = Math.max(maxLen, i - left + 1);
        }

        return maxLen;
    }
}
