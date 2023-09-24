package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2023-05-14 7:18
 */
public class Number1054 {

    public int[] rearrangeBarcodes1(int[] barcodes) {

        int n = barcodes.length;
        int[] cnt = new int[10001];
        for(int barcode : barcodes)
            cnt[barcode]++;

        List<Integer> items = new ArrayList<Integer>();
        for(int i = 1; i < cnt.length; i++){
            if(cnt[i] != 0)
                items.add(i);
        }

        items.sort((o1, o2) -> cnt[o2] - cnt[o1]);

        int[] cur = new int[n];
        int j = 0;
        for(int item : items){
            for(int i = 0; i < cnt[item]; i++)
                cur[j++] = item;
        }

        int[] res = new int[n];
        j = 0;

        for(int i = 0; i < n; i += 2)
            res[i] = cur[j++];

        for(int i = 1; i < n; i += 2)
            res[i] = cur[j++];


        return res;
    }

    public int[] rearrangeBarcodes2(int[] barcodes) {

        int n = barcodes.length;
        int[] cnt = new int[10001];
        for(int barcode : barcodes)
            cnt[barcode]++;

        PriorityQueue<Integer> items = new PriorityQueue<Integer>((o1, o2) -> cnt[o2] - cnt[o1]);
        for(int i = 1; i < cnt.length; i++){
            if(cnt[i] != 0)
                items.offer(i);
        }

        int[] cur = new int[n];
        int j = 0;
        while(!items.isEmpty()){
            int item = items.poll();
            for(int i = 0; i < cnt[item]; i++)
                cur[j++] = item;
        }

        int[] res = new int[n];
        j = 0;
        for(int i = 0; i < n; i += 2)
            res[i] = cur[j++];

        for(int i = 1; i < n; i += 2)
            res[i] = cur[j++];

        return res;
    }

}
