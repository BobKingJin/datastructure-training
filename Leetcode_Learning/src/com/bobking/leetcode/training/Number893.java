package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Number893 {

    // 参考：https://leetcode-cn.com/problems/groups-of-special-equivalent-strings/solution/te-shu-deng-jie-zi-fu-chuan-zu-by-leetcode/
    public int numSpecialEquivGroups(String[] words) {

        if(words == null || words.length == 0)
            return 0;

        Set<String> res = new HashSet<String>();
        for (String word : words) {
            // count 长度为 52 的原因是因为 分为偶数和奇数部分，每个部分最多为 26 位
            int[] count = new int[52];
            for (int i = 0; i < word.length(); i++)
                //                           i % 2 == 0 判断是否为偶数
                count[word.charAt(i) - 'a' + 26 * (i % 2)]++;
            res.add(Arrays.toString(count));
        }

        return res.size();
    }
}
