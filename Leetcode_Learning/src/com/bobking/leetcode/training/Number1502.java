package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Number1502 {

    public boolean canMakeArithmeticProgression(int[] arr) {

        if (null == arr)
            return false;

        if (arr.length <= 2)
            return true;

        Set<Integer> store = new HashSet<Integer>(1024);
        int minest = 10000000;
        int min = 10000000;
        for (int i = 0; i < arr.length; i++) {
            // 找到最小的两个
            store.add(arr[i]);
            if (arr[i] < minest) {
                int temp = minest;
                minest = arr[i];
                if (temp < min)
                    min = temp;
                continue;
            } else if (arr[i] < min) {
                min = arr[i];
                continue;
            }
        }

        int gap = min - minest;
        if (gap == 0)
            return store.size() == 1;

        for (int i = 2; i < arr.length; i++) {

            if (store.contains(min + gap * (i - 1))) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }
}
