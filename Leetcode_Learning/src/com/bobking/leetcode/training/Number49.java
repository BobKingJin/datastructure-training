package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Number49 {

    // 参考：https://leetcode-cn.com/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
    public List<List<String>> groupAnagrams1(String[] strs) {

        if (strs == null || strs.length < 1)
            return null;

        List<List<String>> res = new ArrayList<List<String>>();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for (String str : strs) {
            char[] ch = str.toCharArray();
            // 每个字母异位词进行排序之后都相同
            Arrays.sort(ch);
            if (map.containsKey(new String(ch))) {
                map.get(new String(ch)).add(str);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(str);
                map.put(new String(ch), list);
            }
        }

        for (String str : map.keySet())
            res.add(map.get(str));

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
    public List<List<String>> groupAnagrams2(String[] strs) {

        if (strs == null || strs.length < 1)
            return null;

        List<List<String>> res = new ArrayList<List<String>>();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for (String str : strs) {
            char[] ch = str.toCharArray();
            int[] count = new int[26];
            // 因为所有源单词中的字母都恰好只用一次，因此不会出现重复字符
            for (char c : ch)
                count[c - 'a']++;
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    sb.append((char) i + 'a');
                    sb.append(count[i]);
                }
            }
            if (map.containsKey(sb.toString())) {
                map.get(sb.toString()).add(str);
            } else {
                List<String> list = new ArrayList<String>();
                list.add(str);
                map.put(sb.toString(), list);
            }
        }

        for (String str : map.keySet())
            res.add(map.get(str));

        return res;
    }
}
