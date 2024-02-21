package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Date: 2024/2/21 23:46
 * @Author: BobKing
 * @Description:
 */
public class Number3039 {

    // 参考: https://leetcode.cn/problems/apply-operations-to-make-string-empty/solutions/2643734/tong-ji-zi-mu-chu-xian-ci-shu-he-zui-hou-0r5g/
    public String lastNonEmptyString(String s) {

        int[] cnt = new int[26];
        int[] last = new int[26];
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int b = charArray[i] - 'a';
            cnt[b]++;
            last[b] = i;
        }

        List<Integer> ids = new ArrayList<Integer>();
        int mx = Arrays.stream(cnt).max().orElseThrow();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == mx)
                ids.add(last[i]);
        }

        Collections.sort(ids);
        StringBuilder t = new StringBuilder(ids.size());
        for (int i : ids)
            t.append(charArray[i]);

        return t.toString();
    }
}
