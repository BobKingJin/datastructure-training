package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-10-18 23:11
 */
public class Number763 {

    public List<Integer> partitionLabels(String s) {

        List<Integer> list = new ArrayList<Integer>();

        if(s == null || s.length() == 0)
            return list;

        // 对字符串中出现的每个字符最后出现的位置做一个记录
        int[] lastAppear = new int[26];
        for(int i = 0 ; i < s.length(); i++)
            lastAppear[s.charAt(i) - 'a'] = i;

        // 这个角标用来对整个字符串进行来遍历
        int index = 0;
        // 这个角标用来记录字符出现的最后一个位置
        int lastIndex = -1;

        while(index < s.length()) {

            lastIndex = lastAppear[s.charAt(index) - 'a'];
            // 对 (index + 1) - lastIndex 这个范围中的每个字符进行一个判断
            for(int j = index + 1 ; j < lastIndex ; j++) {
                if(lastAppear[s.charAt(j) - 'a'] > lastIndex)
                    lastIndex = lastAppear[s.charAt(j) - 'a'];
            }
            // 对 index - lastIndex 这个范围中的字符做一个划分
            list.add(lastIndex - index + 1);
            index = lastIndex + 1;
        }

        return list;
    }
}
