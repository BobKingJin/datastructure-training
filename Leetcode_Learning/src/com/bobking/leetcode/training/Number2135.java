package com.bobking.leetcode.training;

import java.util.HashSet;

/**
 * @author BobKing
 * @create 2023-02-02 10:25
 */
public class Number2135 {

    // 参考：https://leetcode.cn/problems/count-words-obtained-after-adding-a-letter/solution/ni-xiang-si-wei-wei-yun-suan-ha-xi-biao-l4153/
    public int wordCount(String[] startWords, String[] targetWords) {

        // 1. 前期铺垫：
        // 因为字符数组中的任一字符串中的每个字母最多出现一次
        // 所以可以取字符串中每一个字符用其 ascii码 - 'a' 进行左移位运算最终得到结果 mask, 不同的字符将体现在 mask 的不同二进制位上
        // 如果想去掉某个字符, 只需要用该字符 ascii - 'a' 进行左移位运算得到的结果再与该字符串的 mask 进行异或运算, 那么代表该字符的位就被
        // 置为 0, 剩下的结果就相当于是取掉该字符后得到的 mask
        // 2. 算法：
        // 1) 计算出 startWords 里每一个字符串 startWords[i] 的 mask, 并存入 HashSet
        // 2) 计算出 targetWords 里面一个字符串 targetWords[i] 的 mask
        // 3) 再遍历 targetWords[i] 的每一个字符 ch, 尝试用该字符的 mask 异或 1 << (ch - 'a'), 去掉 ch 字符代表的那一个二进位位
        // 4) 如果 HashSet 中包含该字符串, 表示当前字符串 targetWords[i] 可以被转换, 那么记录结果的变量 res++, 并且无需对该字符继续进判断,
        //    跳出循环对下一个字符串进行相同的操作

        HashSet<Integer> set = new HashSet<Integer>();
        int res = 0;

        for(String word : startWords) {
            Integer mask = 0;
            for(char ch : word.toCharArray())
                mask = mask | (1 << (ch - 'a'));
            set.add(mask);
        }

        for(String word : targetWords) {
            Integer mask = 0;
            char[] wordArray =  word.toCharArray();
            for(char ch : wordArray)
                mask = mask | (1 << (ch - 'a'));
            for(char ch : wordArray) {
                if(set.contains(mask ^ (1 << (ch - 'a')))) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
