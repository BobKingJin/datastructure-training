package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-10-30 8:58
 */
public class LCR120 {

    public int findRepeatDocument(int[] documents) {

        int i = 0;

        while(i < documents.length) {
            if(documents[i] == i) {
                i++;
                continue;
            }

            if(documents[documents[i]] == documents[i])
                return documents[i];
            int tmp = documents[i];
            documents[i] = documents[tmp];
            documents[tmp] = tmp;
        }
        return -1;
    }
}
