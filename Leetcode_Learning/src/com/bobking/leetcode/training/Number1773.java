package com.bobking.leetcode.training;

import java.util.List;

/**
 * @author BobKing
 * @create 2022-10-29 9:36
 */
public class Number1773 {

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {

        int a = -1;
        int count = 0;

        if (ruleKey.equals("type")) {
            a = 0;
        } else if (ruleKey.equals("color")) {
            a = 1;
        } else {
            a = 2;
        }

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).get(a).equals(ruleValue))
                count++;
        }

        return count;
    }
}
