package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-04-03 14:55
 */
public class Number744 {

    // 参考：https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/solution/by-ac_oier-to07/
    public char nextGreatestLetter(char[] letters, char target) {

        int l = 0;
        int r = letters.length - 1;

        while (l < r) {

            int mid = (l + r) >> 1;
            if (letters[mid] > target) {
                r = mid;
            } else{
                l = mid + 1;
            }
        }

        return letters[r] > target ? letters[r] : letters[0];
    }
}
