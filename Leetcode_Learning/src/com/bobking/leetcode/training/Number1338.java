package com.bobking.leetcode.training;

import java.util.*;

/**
 * @Date: 2023/12/3 11:12
 * @Author: BobKing
 * @Description:
 */
public class Number1338 {

    public int minSetSize(int[] arr) {

        int sum = 0;
        int count = 0;
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = arr.length;
        int halfValues = length / 2;
        for (Integer item : arr) {
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }

        List<Integer> list = new ArrayList<Integer>();
        for (Map.Entry item : map.entrySet()) {
            int value = (int) item.getValue();
            list.add(value);
        }
        Collections.sort(list);
        for (int i = list.size() - 1; i >= 0; i--) {
            if (sum >= halfValues)
                break;
            sum += list.get(i);
            count++;
        }
        return count;
    }
}
