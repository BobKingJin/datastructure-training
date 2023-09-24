package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-05-14 12:10
 */
public class Number89 {

    // 参考：https://leetcode.cn/problems/gray-code/solution/gong-shui-san-xie-dui-cheng-xing-gou-zao-9ap1/
    public List<Integer> grayCode(int n) {

        // 假定 k 位格雷码序列长度为 n，将这 k 位的格雷序列进行翻转，并追加到原有序列的尾部
        // 得到长度为 2 * n 的序列，此时新序列的前后两部分均为合法的格雷码
        // 考虑如何进行解决衔接点的合法性：可以对于序列的后半（翻转而来）的部分中的每个数进行「尾部」追加 1 的操作
        // 确保链接点的两个数只有有一位二进制位不同，同时并不影响前后两半部分的合法性
        // 而且由于后半部分本身是由前半部分翻转而来，序列中的第一个数和最后一个数原本为同一个值，经过追加 1 的操作之后，
        // 首尾两个数的二进制表示只有一位不同，整个序列的合法性得以保证
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        while (n-- > 0) {
            int m = res.size();
            for (int i = m - 1; i >= 0; i--) {
                res.set(i, res.get(i) << 1);
                res.add(res.get(i) + 1);
            }
        }

        return res;
    }
}
