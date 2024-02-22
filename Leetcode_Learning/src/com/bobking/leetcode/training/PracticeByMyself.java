package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2024/2/21 0:30
 * @Author: BobKing
 * @Description:
 */
public class PracticeByMyself {

    private String[] map = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public List<String> letterCombinations1(String digits) {

        if (digits == null || digits.length() < 1)
            return new ArrayList<String>();

        List<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        backTrace(digits, 0, sb, res);
        return res;
    }

    private void backTrace(String digits, int index, StringBuilder sb, List<String> res) {

        if (index == digits.length())
            return;

        String ch = map[digits.charAt(index) - '0'];
        for (int i = 0; i < ch.length(); i++) {
            sb.append(ch.charAt(i));
            backTrace(digits, index + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinations2(String digits) {

        if(digits == null || digits.length() < 1 || digits.trim().equals(""))
            return new ArrayList<String>();

        List<String> res = new ArrayList<String>();
        res.add("");

        for(int i = 0; i < digits.length(); i++){
            String str = map[digits.charAt(i) - '0'];
            for(int j = 0; j < res.size(); j++){
                String temp = res.remove(0);
                for(int k = 0; k < str.length(); k++){
                    res.add(temp + str.charAt(k));
                }
            }
        }




        return null;
    }






}
