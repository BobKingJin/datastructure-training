package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-12-20 11:38
 */
public class Number482 {

    // 参考：https://leetcode.cn/problems/license-key-formatting/solution/gong-shui-san-xie-jian-dan-zi-fu-chuan-m-piya/
    public String licenseKeyFormatting(String s, int k) {

        StringBuilder sb = new StringBuilder();

        // 从后往前处理，避免对首个分区的分情况讨论和取余操作
        for (int i = s.length() - 1, cnt = 0; i >= 0; i--) {
            if (s.charAt(i) == '-')
                continue;
            if (cnt == k && (cnt = 0) >= 0)
                sb.append("-");
            sb.append(s.charAt(i));
            cnt++;
        }
        return sb.reverse().toString().toUpperCase();
    }
}
