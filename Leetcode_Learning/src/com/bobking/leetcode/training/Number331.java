package com.bobking.leetcode.training;

import java.util.LinkedList;

public class Number331 {

    // 参考：https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/solution/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
    public boolean isValidSerialization1(String preorder) {

        LinkedList<String> stack = new LinkedList<String>();

        for (String s : preorder.split(",")) {

            stack.push(s);
            while (stack.size() >= 3
                    && stack.get(0).equals("#")
                    && stack.get(1).equals("#")
                    && !stack.get(2).equals("#")) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }

        return stack.size() == 1 && stack.pop().equals("#");
    }

    // 参考：https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/solution/pai-an-jiao-jue-de-liang-chong-jie-fa-zh-66nt/
    public boolean isValidSerialization2(String preorder) {

        // 在树（甚至图）中，所有节点的入度之和等于出度之和。可以根据这个特点判断输入序列是否为有效的
        int diff = 1;
        for(String s : preorder.split(",")){

            diff--;
            // 每加入一个节点 都要先减去一个入度
            // 若该节点是非空节点 还要再加上两个出度
            // 遍历完之前，出度是大于等于入度的
            // 1个出度认为提供一个挂载点
            // 1个入度认为消耗一个挂载点
            // 若小于等于 消耗的挂载点 大于等于 提供的挂载点
            // 不可能再继续遍历挂载剩下的节点了
            if (diff < 0)
                return false;

            if(!s.equals("#"))
                diff += 2;
        }

        return diff == 0;
    }

}
