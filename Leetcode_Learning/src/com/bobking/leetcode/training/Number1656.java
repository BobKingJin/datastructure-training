package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Number1656 {

    private class OrderedStream {

        String[] ss = new String[1010];
        int idx;
        int n;

        public OrderedStream(int n) {
            Arrays.fill(ss, "");
            this.idx = 1;
            this.n = n;
        }

        public List<String> insert(int idKey, String value) {

            ss[idKey] = value;
            List<String> ans = new ArrayList<String>();

            while (ss[idx].length() == 5)
                ans.add(ss[idx++]);

            return ans;
        }
    }
}
