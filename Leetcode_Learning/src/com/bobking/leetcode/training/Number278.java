package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2023-11-04 7:58
 */
public class Number278 extends VersionControl {

    public int firstBadVersion(int n) {

        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}

class VersionControl {
    boolean isBadVersion(int version) {
        return true;
    }
}
