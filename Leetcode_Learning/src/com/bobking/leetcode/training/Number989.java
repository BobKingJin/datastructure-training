package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Number989 {

    public List<Integer> addToArrayForm1(int[] num, int k) {

        LinkedList<Integer> res = new LinkedList<Integer>();

        if (num == null || num.length == 0 || k < 0)
            return res;

        String str = String.valueOf(k);
        int[] temp = new int[str.length()];
        for (int i = 0; i < temp.length; i++)
            temp[i] = Integer.parseInt(String.valueOf(str.charAt(i)));

        int count = 0;
        int ca = 0;

        int numIndex = num.length - 1;
        int tempIndex = temp.length - 1;
        while (numIndex >= 0 || tempIndex >= 0) {

            count = ((numIndex >= 0 ? num[numIndex] : 0) + (tempIndex >= 0 ? temp[tempIndex] : 0) + ca) % 10;
            ca = ((numIndex >= 0 ? num[numIndex] : 0) + (tempIndex >= 0 ? temp[tempIndex] : 0) + ca) / 10;

            res.addFirst(count);

            if (numIndex >= 0)
                numIndex--;
            if (tempIndex >= 0)
                tempIndex--;
        }

        if (ca != 0)
            res.addFirst(ca);

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/add-to-array-form-of-integer/solution/989-ji-zhu-zhe-ge-jia-fa-mo-ban-miao-sha-8y9r/
    public List<Integer> addToArrayForm2(int[] num, int k) {

        // 可以用 LinkeList，或者 ArrayList 往后加，最后反转
        List<Integer> res = new ArrayList<Integer>();

        if (num == null || num.length == 0 || k < 0)
            return res;

        int i = num.length - 1;
        int sum = 0;
        int ca = 0;

        while (i >= 0 || k != 0) {

            int x = i >= 0 ? num[i] : 0;
            int y = k != 0 ? k % 10 : 0;

            sum = x + y + ca;
            ca = sum / 10;
            k = k / 10;

            i--;
            // 后来的把先来的往后推
            res.add(0, sum % 10);
        }
        if (ca != 0)
            res.add(0, ca);
        return res;
    }

}
