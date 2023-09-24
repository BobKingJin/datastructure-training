package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-03-29 10:52
 */
public class Number187 {

    // 参考：https://leetcode-cn.com/problems/repeated-dna-sequences/solution/gong-shui-san-xie-yi-ti-shuang-jie-hua-d-30pg/
    public List<String> findRepeatedDnaSequences1(String s) {

        if(s == null || s.length() == 0)
            return new ArrayList<String>();
        
        List<String> res = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i + 10 <= s.length(); i++) {

            String cur = s.substring(i, i + 10);
            int cnt = map.getOrDefault(cur, 0);
            if (cnt == 1)
                res.add(cur);
            map.put(cur, cnt + 1);
        }

        return res;
    }

    int N = (int)1e5+10;
    int P = 131313;
    // 使用一个与字符串 s 等长的哈希数组 h[]，以及次方数组 p[]
    int[] h = new int[N];
    int[] p = new int[N];

    // 参考：https://leetcode-cn.com/problems/repeated-dna-sequences/solution/gong-shui-san-xie-yi-ti-shuang-jie-hua-d-30pg/
    public List<String> findRepeatedDnaSequences2(String s) {

        if(s == null || s.length() == 0)
            return new ArrayList<String>();

        // 当需要计算子串 s[i...j] 的哈希值，只需要利用前缀和思想 h[j] - h[i - 1] * p[j - i + 1]
        // 即可在 O(1) 时间内得出哈希值（与子串长度无关）
        int n = s.length();
        List<String> res = new ArrayList<String>();
        p[0] = 1;

        // h[i] 和 p[i] 会溢出，溢出就会变为负数，当且仅当两个哈希值溢出程度与 Integer.MAX_VALUE 呈不同的倍数关系时
        // 会产生错误结果（哈希冲突）
        for (int i = 1; i <= n; i++) {
            h[i] = h[i - 1] * P + s.charAt(i - 1);
            p[i] = p[i - 1] * P;
        }

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 1; i + 10 - 1 <= n; i++) {
            int j = i + 10 - 1;
            int hash = h[j] - h[i - 1] * p[j - i + 1];
            int cnt = map.getOrDefault(hash, 0);
            if (cnt == 1)
                res.add(s.substring(i - 1, i + 10 - 1));
            map.put(hash, cnt + 1);
        }

        return res;
    }
    

    
    
}
