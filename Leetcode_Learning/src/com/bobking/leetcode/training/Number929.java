package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-06-04 19:58
 */
public class Number929 {

    public int numUniqueEmails(String[] emails) {

        Set<String> set = new HashSet<String>();
        for (String s : emails) {
            StringBuilder sb = new StringBuilder();
            int n = s.length();
            int i = 0;
            boolean ok = true;
            while (i < n) {
                char c = s.charAt(i);
                if (c == '@')
                    break;
                if (c == '.' && ++i >= 0)
                    continue;
                if (c == '+')
                    ok = false;
                if (ok)
                    sb.append(c);
                i++;
            }

            set.add(sb.append(s.substring(i)).toString());
        }

        return set.size();
    }
}
