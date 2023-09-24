package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2021-03-14 8:54
 */
public class Number524 {

    public String findLongestWord1(String s, List<String> d) {

        if (s == null || s.length() < 1 || d == null || d.size() < 1)
            return "";

        HashSet<String> set = new HashSet<String>(d);
        List<String> list = new ArrayList<String>();

        generate(s, "", 0, list);

        String res = "";
        for (String element : list) {
            if (set.contains(element)) {
                if (element.length() > res.length() || (element.length() == res.length() && element.compareTo(res) < 0))
                    res = element;
            }
        }

        return res;
    }

    // 通过删减 s 中的字符，得到所有可能
    private void generate(String s, String str, int index, List<String> list) {

        if (index == s.length()) {
            list.add(str);
        } else {
            generate(s, str + s.charAt(index), index + 1, list);
            generate(s, str, index + 1, list);
        }
    }

    public String findLongestWord2(String s, List<String> d) {

        if (s == null || s.length() < 1 || d == null || d.size() < 1)
            return "";

        HashSet<String> set = new HashSet<String>(d);
        List<String> list = new ArrayList<String>();

        // 因为例如 s.length() = 3，那么 2^3 - 1 = 7，需要判断 0 - 7 这个范围
        for (int i = 0; i < (1 << s.length()); i++) {

            String str = "";
            // 即判断 i 中有哪些位为 1
            for (int j = 0; j < s.length(); j++) {
                if (((i >>> j) & 1) == 1)
                    str = str + s.charAt(j);
            }
            list.add(str);
        }

        String res = "";
        for (String element : list) {
            if (set.contains(element)) {
                if (element.length() > res.length() || (element.length() == res.length() && element.compareTo(res) < 0))
                    res = element;
            }
        }
        return res;
    }

    public String findLongestWord3(String s, List<String> d) {

        // 先对List中的元素进行排序
        Collections.sort(d, new Comparator<String>() {

            @Override
            public int compare(String str1, String str2) {

                if (str1.length() == str2.length())
                    return str1.compareTo(str2);
                return str2.length() - str1.length();
            }
        });

        for (String str : d) {
            // 判断s是否能通过删除某些字符之后等于str
            if (hasSubSequence(s, str))
                return str;
        }

        return "";
    }

    private boolean hasSubSequence(String s, String subSequence) {

        // 指向字符串s
        int index1 = 0;
        // 指向子序列
        int index2 = 0;

        while (index1 < s.length() && index2 < subSequence.length()) {
            // 相等则移动 index2
            if (s.charAt(index1) == subSequence.charAt(index2))
                index2++;
            // 不等则移动 index1
            index1++;
        }
        return index2 == subSequence.length();
    }
}
