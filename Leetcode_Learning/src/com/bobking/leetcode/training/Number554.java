package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-09-11 22:35
 */
public class Number554 {

    // 参考：https://leetcode.cn/problems/brick-wall/solution/gong-shui-san-xie-zheng-nan-ze-fan-shi-y-gsri/
    public int leastBricks(List<List<Integer>> wall) {

        int n = wall.size();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0, sum = 0; i < n; i++, sum = 0) {
            for (int cur : wall.get(i)) {
                sum += cur;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            // 不能从两边穿过，需要 remove 掉最后一个
            map.remove(sum);
        }

        int ans = n;

        for (int u : map.keySet()) {
            int cnt = map.get(u);
            ans = Math.min(ans, n - cnt);
        }

        return ans;
    }
}
