package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 2025/8/10 16:42
 * @Author: BobKing
 * @Description:
 */
public class Number869 {

    private final Set<String> powTwoSortedStrSet = new HashSet<>();
    private final int MAX_N = 1_000_000_000;

    private void init() {
        for (int i = 1; i < MAX_N; i <<= 1) {
            String s = intToSortedStr(i);
            powTwoSortedStrSet.add(s);
        }
    }

    private String intToSortedStr(int n) {
        char[] s = String.valueOf(n).toCharArray();
        Arrays.sort(s);
        return new String(s);
    }

    // 参考: https://leetcode.cn/problems/reordered-power-of-2/solutions/3748346/ni-xiang-si-wei-pythonjavacgo-by-endless-ehey/?envType=daily-question&envId=2025-08-10
    public boolean reorderedPowerOf2(int n) {
        init();
        String s = intToSortedStr(n);
        return powTwoSortedStrSet.contains(s);
    }

}
