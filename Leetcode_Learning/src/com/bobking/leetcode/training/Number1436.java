package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-10-30 15:32
 */
public class Number1436 {

    // 参考：https://leetcode.cn/problems/destination-city/solution/gong-shui-san-xie-jian-dan-fang-jia-mo-n-y47c/
    public String destCity(List<List<String>> paths) {

        Map<String, String> map = new HashMap<String, String>();

        for (List<String> p : paths)
            map.put(p.get(0), p.get(1));

        String ans = paths.get(0).get(0);
        while (map.containsKey(ans))
            ans = map.get(ans);

        return ans;
    }
}
