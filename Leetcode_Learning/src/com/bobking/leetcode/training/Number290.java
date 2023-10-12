package com.bobking.leetcode.training;

import java.util.HashMap;

/**
 * @author BobKing
 * @create 2023-10-12 7:21
 */
public class Number290 {

    // 参考: https://leetcode.cn/problems/word-pattern/solutions/192714/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--53/
    public boolean wordPattern1(String pattern, String s) {

        String[] array1 = s.split(" ");

        if (array1.length != pattern.length())
            return false;

        String[] array2 = pattern.split("");

        // 两个方向的映射
        return wordPatternHelper1(array1, array2) && wordPatternHelper1(array2, array1);
    }

    // array1 到 array2 的映射
    private boolean wordPatternHelper1(String[] array1, String[] array2) {

        HashMap<String, String> map = new HashMap<String, String>();

        for (int i = 0; i < array1.length; i++) {
            String key = array1[i];
            if (map.containsKey(key)) {
                if (!map.get(key).equals(array2[i]))
                    return false;
            } else {
                map.put(key, array2[i]);
            }
        }
        return true;
    }

    // 参考: https://leetcode.cn/problems/word-pattern/solutions/192714/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--53/
    public boolean wordPattern2(String pattern, String str) {

        String[] array = str.split(" ");

        if(array.length!=pattern.length())
            return false;

        // 判断映射后的结果是否相等
        return wordPatternHelper2(pattern.split("")).equals(wordPatternHelper2(array));
    }

    private String wordPatternHelper2(String[] array) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int count = 1;

        // 保存映射后的结果
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            // 是否已经映射过
            if (map.containsKey(array[i])) {
                sb.append(map.get(array[i]));
            } else {
                sb.append(count);
                map.put(array[i], count);
                count++;
            }
        }

        return sb.toString();
    }


}
