package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number179 {

    // 参考：https://leetcode-cn.com/problems/largest-number/solution/gong-shui-san-xie-noxiang-xin-ke-xue-xi-vn86e/
    public String largestNumber(int[] nums) {

        int n = nums.length;
        String[] ss = new String[n];

        for (int i = 0; i < n; i++)
            ss[i] = "" + nums[i];

        Arrays.sort(ss, (a, b) -> {
            String sa = a + b;
            String sb = b + a ;
            // 大到小
            return sb.compareTo(sa);
        });

        StringBuilder sb = new StringBuilder();
        for (String s : ss)
            sb.append(s);

        int k = 0;
        // 需要处理前导零（最多保留一位）
        while (k < (sb.length() - 1) && sb.charAt(k) == '0')
            k++;

        return sb.substring(k);
    }


}
