package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author BobKing
 * @create 2023-10-25 8:09
 */
public class Number535 {

    // 对于每个 longUrl 都在「大写字母/小写字母/数字」中随机 k = 6 位作为其映射标识，这需要使用一个哈希表 tiny2Origin 进行记录
    // 同时了防止相同的 longUrl 多次调用，确保 encode 服务的「幂等性」，再额外建立哈希表 origin2Tiny 来记录原串和映射标识的对应关系

    // 参考: https://leetcode.cn/problems/encode-and-decode-tinyurl/solutions/1632000/by-ac_oier-ca6o/
    private class Codec {

        Map<String, String> origin2Tiny = new HashMap<String, String>();
        Map<String, String> tiny2Origin = new HashMap<String, String>();
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String prefix = "https://acoier.com/tags/";
        int k = 6;
        Random random = new Random();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            while (!origin2Tiny.containsKey(longUrl)) {
                char[] cs = new char[k];
                for (int i = 0; i < k; i++)
                    cs[i] = str.charAt(random.nextInt(str.length()));
                String cur = prefix + String.valueOf(cs);
                if (tiny2Origin.containsKey(cur))
                    continue;
                tiny2Origin.put(cur, longUrl);
                origin2Tiny.put(longUrl, cur);
            }
            return origin2Tiny.get(longUrl);
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return tiny2Origin.get(shortUrl);
        }
    }
}
