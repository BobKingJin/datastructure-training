package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-05-14 11:46
 */
public class Number412 {

    public List<String> fizzBuzz(int n) {

        List<String> res = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {

            String cur = "";

            if (i % 3 == 0)
                cur += "Fizz";

            if (i % 5 == 0)
                cur += "Buzz";

            if (cur.length() == 0)
                cur = i + "";

            res.add(cur);
        }
        return res;
    }
}
