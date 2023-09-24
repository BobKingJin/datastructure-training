package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BobKing
 * @create 2023-05-18 7:39
 */
public class Number1073 {

    public int[] addNegabinary(int[] arr1, int[] arr2) {

        List<Integer> list = new ArrayList<Integer>();

        if (arr1.length < arr2.length) {
            int[] temp = arr1;
            arr1 = arr2;
            arr2 = temp;
        }

        int l = arr1.length - 1;
        int r = arr2.length - 1;
        int k = 0;

        while (l >= 0) {
            int m = arr1[l] + k;
            if (r >= 0)
                m += arr2[r];
            k = 0;
            if (m >= 2) {
                k = -1;
                m -= 2;
            } else if (m == -1) {
                m = 1;
                k = 1;
            }
            list.add(m);
            l--;
            r--;
        }
        if (k == 1) {
            list.add(1);
        } else if (k == -1) {
            list.add(1);
            list.add(1);
        }

        int size = list.size();
        l = size - 1;
        while (list.get(l) == 0 && l > 0)
            l--;

        int[] arr = new int[l + 1];
        for (int i = 0; l >= 0; i++)
            arr[i] = list.get(l--);

        return arr;
    }
}
