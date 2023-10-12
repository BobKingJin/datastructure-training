package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BobKing
 * @create 2023-10-11 7:17
 */
public class Number638 {

    int ans = 0x3f3f3f3f;
    List<Integer> price;
    List<Integer> needs;
    List<List<Integer>> special;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    int n;
    int m;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {

        // 对于某个 need[i] 而言，既可以「单买」也可以使用「礼包形式购买」
        // 先对「单买」情况进行预处理，将其转换为「礼包」形式。若 price[0]=100，则使用礼包 [1, 0, 0, ..., 0, 100] 来代指
        // 然后再预处理每个礼包最多选多少个，并使用哈希表进行存储
        // 最后使用 DFS 对每个「礼包」如何选择进行爆搜

        this.price = price;
        this.special = special;
        this.needs = needs;
        n = price.size();

        List<Integer> temp = new ArrayList<Integer>();

        for (int i = 0; i < n; i++)
            temp.add(0);

        for (int i = 0; i < n; i++) {
            List<Integer> clone = new ArrayList<Integer>(temp);
            clone.set(i, 1);
            clone.add(price.get(i));
            special.add(clone);
        }

        m = special.size();
        for (int i = 0; i < m; i++) {
            List<Integer> x = special.get(i);
            int k = 0;
            for (int j = 0; j < n; j++) {
                int a = x.get(j);
                int b = needs.get(j);
                if (a == 0 || b == 0)
                    continue;
                // 不能购买超出购物清单指定数量的物品，即使那样会降低整体价格
                if (a > b) {
                    k = 0;
                    break;
                }
                k = (k == 0) ? b / a : Math.min(k, b / a);
            }
            map.put(i, k);
        }
        dfs(0, needs, 0);
        return ans;
    }

    private void dfs(int u, List<Integer> list, int cur) {

        if (cur >= ans)
            return;

        int cnt = 0;
        for (int i = 0; i < n; i++)
            cnt += list.get(i);

        if (cnt == 0) {
            ans = cur;
            return;
        }

        if (u == m)
            return;

        List<Integer> x = special.get(u);

        out:
        // map 中记录了每个礼包最多选择多少个
        for (int k = 0; k <= map.get(u); k++) {
            List<Integer> clist = new ArrayList<Integer>(list);
            for (int i = 0; i < n; i++) {
                int a = x.get(i);
                int b = clist.get(i);
                if (a * k > b)
                    break out;
                clist.set(i, b - a * k);
            }
            dfs(u + 1, clist, cur + k * x.get(n));
        }
    }
}
