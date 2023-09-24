package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Number1331 {

    public int[] arrayRankTransform(int[] arr) {

        int[] clone = arr.clone();
        Arrays.sort(clone);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = arr.length;
        int idx = 0;
        for (int i : clone) {
            if (!map.containsKey(i))
                map.put(i, ++idx);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = map.get(arr[i]);

        return ans;
    }
}
