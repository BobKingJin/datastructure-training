package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-08-06 10:00
 */
public class Number1408 {

    // 参考：https://leetcode.cn/problems/string-matching-in-an-array/solution/liang-ci-xun-huan-jie-jue-bu-shi-qian-tao-xun-huan/
    public List<String> stringMatching1(String[] words) {

        // 两次循环
        // 第一次遍历数组拼接成一个长字符串 S
        // 第二次遍历数组查找字符串在 S 中出现的位置，如果是子串，那么在 S 中的出现次数一定 >= 2，那么起始跟结束的位置索引一定是不一样的，如果一样说明不是子串
        // 注意：为了避免前一字符串的尾部与后一字符串的头部构成混淆项，各字符串用,隔开拼接

        List<String> list = new ArrayList<String>();

        if (words.length == 0)
            return list;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++){
            String str = words[i];
            builder.append(str + ",");
        }

        for (int i = 0; i < words.length; i++){
            String str = words[i];
            if (builder.toString().indexOf(str) != builder.toString().lastIndexOf(str))
                list.add(str);
        }

        return list;
    }

    // 参考：https://leetcode.cn/problems/string-matching-in-an-array/solution/by-ac_oier-k03v/
    public List<String> stringMatching2(String[] words) {

        List<String> ans = new ArrayList<String>();
        int n = words.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                if (words[j].indexOf(words[i]) >= 0) {
                    ans.add(words[i]);
                    break;
                }
            }
        }

        return ans;
    }

}
