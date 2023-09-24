package com.bobking.leetcode.training;

public class Number1290 {

    public int getDecimalValue1(ListNode head) {

        int res = 0;

        while(head != null){
            res = res * 2 + head.val;
            head = head.next;
        }

        return res;
    }

    int count = 0;
    int res = 0;

    public int getDecimalValue2(ListNode head) {

        if(head == null)
            return 0;

        res += getDecimalValue2(head.next) + head.val * Math.pow(2, count);
        count++;
        return (int)res;
    }
}
