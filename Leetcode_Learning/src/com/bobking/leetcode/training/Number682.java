package com.bobking.leetcode.training;

import java.util.ArrayList;

/**
 * @author BobKing
 * @create 2022-03-26 12:11
 */
public class Number682 {

    public int calPoints(String[] ops) {

        if(ops == null || ops.length == 0)
            return 0;

        ArrayList<Integer> record = new ArrayList<Integer>();

        for(String str : ops){

            if(str.equals("+")){
                int score = record.get(record.size() - 1) + record.get(record.size() - 2);
                record.add(score);
            }else if(str.equals("D")){
                int score = record.get(record.size() - 1) * 2;
                record.add(score);
            }else if(str.equals("C")){
                record.remove(record.size() - 1);
            }else{
                record.add(Integer.parseInt(str));
            }
        }

        int res = 0;

        for(Integer i : record)
            res += i;

        return res;
    }
}
