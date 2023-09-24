package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BobKing
 * @create 2022-12-13 11:05
 */
public class Number1481 {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int num : arr)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int size = map.size();
        int[] cnt = new int[size];
        int m = 0;

        for(int key : map.keySet())
            cnt[m++] = map.get(key);

        Arrays.sort(cnt);
        // 排序之后，从小到大，如果 k 大于这个数字次数，那么这个数字就可以移除
        for(int c : cnt){
            if(k >= c){
                k -= c;
                size--;
            }else{
                break;
            }
        }
        return size;
    }
}
