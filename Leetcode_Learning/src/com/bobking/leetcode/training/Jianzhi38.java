package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Date: 2026/6/26 15:40
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi38 {

    // 参考: Number47
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return res;
        }
        // 转字符数组
        char[] charArr = str.toCharArray();
        // 按字典序排序
        Arrays.sort(charArr);
        boolean[] vis = new boolean[str.length()];
        StringBuffer sb = new StringBuffer();
        recursion(res, charArr, sb, vis);
        return res;
    }

    public void recursion(ArrayList<String> res, char[] str, StringBuffer sb, boolean[] vis) {

        if (sb.length() == str.length) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < str.length; i++) {
            if (vis[i]) {
                continue;
            }
            if (i > 0 && str[i - 1] == str[i] && !vis[i - 1]) {
                continue;
            }
            vis[i] = true;
            sb.append(str[i]);
            recursion(res, str, sb, vis);
            vis[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
