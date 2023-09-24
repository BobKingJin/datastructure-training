package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-09-09 15:20
 */
public class Number767 {

    // 参考：https://leetcode.cn/problems/reorganize-string/solution/javadai-ma-ji-bai-liao-100de-yong-hu-by-sdwwld/
    public String reorganizeString(String s) {

        char[] alphabetArr = s.toCharArray();
        int[] alphabetCount = new int[26];
        int length = s.length();

        for (int i = 0; i < length; i++)
            alphabetCount[alphabetArr[i] - 'a']++;

        int max = 0;
        int alphabet = 0;
        int threshold = (length + 1) >> 1;

        for (int i = 0; i < alphabetCount.length; i++) {
            if (alphabetCount[i] > max) {
                max = alphabetCount[i];
                alphabet = i;

                if (max > threshold)
                    return "";
            }
        }

        char[] res = new char[length];
        int index = 0;
        // 先把出现次数最多的字符存储在数组下标为偶数的位置上
        while (alphabetCount[alphabet]-- > 0) {
            res[index] = (char) (alphabet + 'a');
            index += 2;
        }

        // 然后再把剩下的字符存储在其他位置上
        for (int i = 0; i < alphabetCount.length; i++) {
            while (alphabetCount[i]-- > 0) {
                if (index >= res.length)
                    index = 1;

                res[index] = (char) (i + 'a');
                index += 2;
            }
        }

        return new String(res);
    }
}
