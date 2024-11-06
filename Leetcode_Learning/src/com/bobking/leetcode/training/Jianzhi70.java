package com.bobking.leetcode.training;

/**
 * @Date: 2024/11/6 10:23
 * @Author: BobKing
 * @Description:
 */
public class Jianzhi70 {

    // 题目: https://www.nowcoder.com/practice/72a5a919508a4251859fb2cfb987a0e6?tpId=13&tqId=23283&ru=/exam/oj/ta&qru=/ta/coding-interviews/question-ranking&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D13%26type%3D13
    // 参考: https://uploadfiles.nowcoder.com/images/20160616/716804_1466088939214_DB8DE8E90C58DADF4C1048A7B110E8E5
    public int rectCover(int target) {

        if (target <= 2)
            return target;

        int pre = 1;
        int cur = 2;
        for (int i = 3; i <= target; i++) {
            cur += pre;
            pre = cur - pre;
        }
        return cur;
    }
}
