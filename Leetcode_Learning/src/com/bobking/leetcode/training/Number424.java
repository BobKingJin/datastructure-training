package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-03 8:10
 */
public class Number424 {

    // 参考：https://leetcode.cn/problems/longest-repeating-character-replacement/solution/tong-guo-ci-ti-liao-jie-yi-xia-shi-yao-shi-hua-don/
    public int characterReplacement(String s, int k) {

        if (s == null)
            return 0;

        int[] map = new int[26];
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        // historyCharMax 保存滑动窗口内相同字母出现次数的 历史 最大值
        // 通过判断窗口宽度 (right - left + 1) 是否大于 historyCharMax + K 来决定窗口是否做滑动，否则窗口就扩张
        int historyCharMax = 0;

        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);
            if (right - left + 1 > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length - left;
    }
}
