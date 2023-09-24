package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number728 {

    // 参考：https://leetcode-cn.com/problems/self-dividing-numbers/solution/by-ac_oier-pvb1/
    public List<Integer> selfDividingNumbers1(int left, int right) {

        List<Integer> res = new ArrayList<Integer>();

        if(left < 1 || right < 1 || left > right)
            return res;

        out:
        for (int i = left; i <= right; i++) {

            int cur = i;
            while (cur != 0) {
                int t = cur % 10;
                if (t == 0 || i % t != 0)
                    continue out;
                cur /= 10;
            }
            res.add(i);
        }

        return res;
    }

    List<Integer> list = new ArrayList<Integer>();

    private void preProcess(){

        out:
        for (int i = 1; i <= 10000; i++) {
            int cur = i;
            while (cur != 0) {
                int u = cur % 10;
                if (u == 0 || i % u != 0)
                    continue out;
                cur /= 10;
            }
            list.add(i);
        }
    }

    // 参考：https://leetcode-cn.com/problems/self-dividing-numbers/solution/by-ac_oier-pvb1/
    public List<Integer> selfDividingNumbers2(int left, int right) {
        
        List<Integer> res = new ArrayList<Integer>();

        preProcess();

        int l = 0;
        int r = list.size() - 1;

        // 通过二分找到 [left, right] 范围内的最小自除数，再从前往后找到所有合法的自除数
        while (l < r) {
            int mid = l + r >> 1;
            if (list.get(mid) >= left){
                r = mid;
            } else{
                l = mid + 1;
            }
        }

        while (r < list.size() && list.get(r) <= right)
            res.add(list.get(r++));

        return res;
    }

    
}
