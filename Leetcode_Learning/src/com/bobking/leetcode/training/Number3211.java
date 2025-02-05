package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2025/2/4 10:52
 * @Author: BobKing
 * @Description:
 */
public class Number3211 {

    // 参考: https://leetcode.cn/problems/generate-binary-strings-without-adjacent-zeros/solutions/2833805/wei-yun-suan-zuo-fa-pythonjavacgo-by-end-6lbt/?envType=daily-question&envId=2025-02-04
    public List<String> validStrings1(int n) {
        List<String> ans = new ArrayList<String>();
        char[] path = new char[n];
        dfs(0, n, path, ans);
        return ans;
    }

    private void dfs(int i, int n, char[] path, List<String> ans) {
        if (i == n) {
            ans.add(new String(path));
            return;
        }

        // 填 1
        path[i] = '1';
        dfs(i + 1, n, path, ans);

        // 填 0
        if (i == 0 || path[i - 1] == '1') {
            path[i] = '0';
            dfs(i + 1, n, path, ans);
        }
    }

    // 参考: https://leetcode.cn/problems/generate-binary-strings-without-adjacent-zeros/solutions/2833805/wei-yun-suan-zuo-fa-pythonjavacgo-by-end-6lbt/?envType=daily-question&envId=2025-02-04
    public List<String> validStrings2(int n) {
        List<String> ans = new ArrayList<String>();
        int mask = (1 << n) - 1;

        for (int x = 0; x < (1 << n); x++) {
            if (((x >> 1) & x) == 0) {
                int i = x ^ mask;
                // 一种生成前导零的写法：在 i 前面插入 1<<n，转成字符串后再去掉插入的 1<<n
                ans.add(Integer.toBinaryString((1 << n) | i).substring(1));
            }
        }
        return ans;
    }


}
