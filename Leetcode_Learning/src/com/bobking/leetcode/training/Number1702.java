package com.bobking.leetcode.training;

import java.util.Arrays;

public class Number1702 {

    // 参考：https://leetcode.cn/problems/maximum-binary-string-after-change/solution/xiang-xi-zheng-ming-wai-jia-dai-ma-shi-x-ht1j/
    public String maximumBinaryString(String binary) {

        // 修改后的最大二进制字符串最终只能含有一个 0

        int n = binary.length();
        int begin = (int)1e6;
        int count = 0;

        for(int i = 0; i < n; i++) {
            if(binary.charAt(i) == '0') {
                count++;
                begin = Math.min(begin, i);
            }
        }

        if(count < 2)
            return binary;

        char[] res = new char[n];
        Arrays.fill(res, '1');
        res[begin + count - 1] = '0';
        return new String(res);
    }
}
