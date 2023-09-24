package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author BobKing
 * @create 2022-07-24 10:15
 */
public class Number2058 {

    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] res = new int[]{-1, -1};
        List<Integer> list = new ArrayList<Integer>();
        ListNode tmp = head;
        ListNode pre = null;

        for (int i = 0; tmp != null; tmp = tmp.next, i++) {

            if (pre == null || tmp.next == null) {
                pre = tmp;
                continue;
            }

            if ((pre.val < tmp.val && tmp.next.val < tmp.val)
                    || (pre.val > tmp.val && tmp.next.val > tmp.val))
                list.add(i);

            pre = tmp;
        }

        Collections.sort(list);

        if (list.size() > 1) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < list.size() - 1; i++)
                min = Math.min(min, list.get(i + 1) - list.get(i));

            res[0] = min;
            res[1] = list.get(list.size() - 1) - list.get(0);
        }

        return res;
    }
}
