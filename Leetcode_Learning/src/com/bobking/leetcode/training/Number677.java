package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-06-26 12:10
 */
public class Number677 {

    // 字典树的实现参考：https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247488490&idx=1&sn=db2998cb0e5f08684ee1b6009b974089&chksm=fd9cb8f5caeb31e3f7f67dba981d8d01a24e26c93ead5491edb521c988adc0798d8acb6f9e9d&token=59039721&lang=zh_CN#rd
    // 参考：https://leetcode.cn/problems/map-sum-pairs/solution/gong-shui-san-xie-jie-he-dfs-de-trie-yun-i4xa/
    private class MapSum {

        int[][] tr = new int[2510][26];
        // 使用 hash 数组记录某个格子被「被标记为结尾的次数」
        int[] hash = new int[2510];
        // 使用 idx 来自增记录到底用了多少个格子
        int idx;

        public MapSum() {

        }

        public void insert(String key, int val) {

            int p = 0;
            for (int i = 0; i < key.length(); i++) {
                int u = key.charAt(i) - 'a';
                if (tr[p][u] == 0)
                    tr[p][u] = ++idx;
                p = tr[p][u];
            }

            hash[p] = val;
        }

        public int sum(String prefix) {

            int p = 0;
            for (int i = 0; i < prefix.length(); i++) {
                int u = prefix.charAt(i) - 'a';
                if (tr[p][u] == 0)
                    return 0;
                p = tr[p][u];
            }
            return dfs(p);
        }

        private int dfs(int p) {

            int ans = hash[p];
            for (int u = 0; u < 26; u++) {
                if (tr[p][u] != 0)
                    ans += dfs(tr[p][u]);
            }

            return ans;
        }
    }
}
