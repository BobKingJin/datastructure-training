package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-01-16 20:41
 */
public class Number1813 {

    // 参考：https://leetcode.cn/problems/sentence-similarity-iii/solution/javac-shuang-zhi-zhen-by-tizzi-0t5r/
    public boolean areSentencesSimilar(String sentence1, String sentence2) {

        if (sentence1.length() > sentence2.length())
            return areSentencesSimilar(sentence2, sentence1);

        String[] arr1 = sentence1.split(" ");
        String[] arr2 = sentence2.split(" ");

        int n = arr1.length;
        int m = arr2.length;
        int l = 0;
        int r = 0;

        while (l < n && arr1[l].equals(arr2[l]))
            l++;
        while (r < n - l && arr1[n - r - 1].equals(arr2[m - r - 1]))
            r++;

        return l + r == n;
    }
}
