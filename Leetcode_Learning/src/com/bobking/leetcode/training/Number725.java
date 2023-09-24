package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-08-21 10:12
 */
public class Number725 {

    // 参考：https://leetcode.cn/problems/split-linked-list-in-parts/solution/gong-shui-san-xie-jing-dian-lian-biao-ju-9yj4/
    public ListNode[] splitListToParts(ListNode head, int k) {


        // 以先对链表进行一次扫描，得到总长度 cnt，再结合需要将将链表划分为 k 份，可知每一份的 最小 分配单位 per
        // 然后从前往后切割出 k 份链表，由于是在原链表的基础上进行，因此这里的切分只需要在合适的位置将节点的 next 指针置空即可
        // 当需要构造出 ans[i] 的链表长度时，首先可以先分配 per 的长度，如果 已处理的链表长度 + 剩余待分配份数 * per < cnt
        // 说明后面「待分配的份数」如果按照每份链表分配 per 长度的话，会有节点剩余，基于「不能均分时，前面的应当比后面长」原则
        // 此时只需为当前 ans[i] 多分一个单位长度即可

        // 扫描链表，得到总长度 cnt
        int cnt = 0;
        ListNode tmp = head;
        while (tmp != null && ++cnt > 0)
            tmp = tmp.next;
        // 理论最小分割长度
        int per = cnt / k;
        // 将链表分割为 k 份（sum 代表已经被处理的链表长度为多少）
        ListNode[] ans = new ListNode[k];
        for (int i = 0, sum = 1; i < k; i++, sum++) {
            ans[i] = head;
            tmp = ans[i];
            // 每次首先分配 per 的长度
            int u = per;
            while (u-- > 1 && ++sum > 0)
                tmp = tmp.next;
            // 当「已处理的链表长度 + 剩余待分配份数 * per < cnt」，再分配一个单位长度
            int remain = k - i - 1;
            if (per != 0 && sum + per * remain < cnt && ++sum > 0)
                tmp = tmp.next;
            head = tmp != null ? tmp.next : null;
            if (tmp != null)
                tmp.next = null;
        }

        return ans;
    }
}
