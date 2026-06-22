package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Date: 2026/6/21 16:35
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi57 {

    public ArrayList<Integer> FindNumbersWithSum1(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();

        for (int i = 0; i < array.length; i++) {
            int temp = sum - array[i];
            if (!mp.containsKey(temp)) {
                mp.put(array[i], i);
            } else {
                res.add(temp);
                res.add(array[i]);
                break;
            }
        }
        return res;
    }

    public ArrayList<Integer> FindNumbersWithSum2(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            if (array[left] + array[right] == sum) {
                res.add(array[left]);
                res.add(array[right]);
                break;
            } else if (array[left] + array[right] > sum) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

}
