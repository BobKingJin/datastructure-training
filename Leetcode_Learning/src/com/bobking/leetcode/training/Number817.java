package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

/**
 * @author BobKing
 * @create 2022-10-12 9:00
 */
public class Number817 {

    // 参考：https://leetcode.cn/problems/linked-list-components/solution/lian-biao-zu-jian-by-leetcode-solution-5f91/
    public int numComponents(ListNode head, int[] nums) {

        // 需要计算组件的个数，只需在链表中计算有多少组件的起始位置即可
        // 当一个节点满足以下条件之一时，它是组件的起始位置：
        // 节点的值在数组 nums 中且节点位于链表起始位置
        // 节点的值在数组 nums 中且节点的前一个点不在数组 nums 中


        Set<Integer> numsSet = new HashSet<Integer>();
        for (int num : nums)
            numsSet.add(num);

        boolean inSet = false;
        int res = 0;

        while (head != null) {
            if (numsSet.contains(head.val)) {
                if (!inSet) {
                    inSet = true;
                    res++;
                }
            } else {
                inSet = false;
            }
            head = head.next;
        }

        return res;
    }
}
