package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.List;

public class Number679 {

    // 参考：https://leetcode.cn/problems/24-game/solution/ying-gai-shi-man-hao-li-jie-de-si-lu-liao-by-xiao_/
    public boolean judgePoint24(int[] cards) {

        List<Double> list = new ArrayList<Double>(4);
        for (int card : cards)
            list.add((double) card);

        return solve(list);
    }

    private boolean solve(List<Double> card){

        // 思路
        // 游戏的第一步是挑出两个数，算出一个新数替代这两个数
        // 然后，在三个数中玩 24 点，再挑出两个数，算出一个数替代它们
        // 然后，在两个数中玩 24 点……
        // 很明显的递归思路。每次递归都会挑出两个数，尝试挑出不同的两数组合：
        // 挑 1、2，基于它，继续递归
        // 挑 1、3，基于它，继续递归
        // 挑 ……
        // 即通过两层 for 循环，枚举所有的两数组合，展开出不同选择所对应的递归分支
        // 挑出的每一对数…
        // 枚举出所有可能的运算操作：加减乘除… ——（对应不同的递归调用）
        // 逐个尝试每一种运算——（选择进入一个递归分支）
        // 传入长度变小的新数组继续递归——（递归计算子问题）
        // 当递归到只剩一个数——（到达了递归树的底部），看看是不是 24
        // 是就返回 true——结束当前递归，并且控制它不进入别的递归分支，整个结束掉
        // 否则返回 false，离开错误的分支，进入别的递归分支，尝试别的运算
        // 剪枝小技巧
        // 当递归返回 true，代表游戏成功，不用继续探索了，剩下的搜索分支全部砍掉。怎么做到？
        // 代码如下。标识变量 isValid 初始为 false，默认会执行 || 后面的递归
        // 一旦某个递归返回真，isValid就变为真，由于 || 的短路特性，后面的递归不会执行
        // 所有递归子调用都这么写，isValid就像一个开关，避免写很多判断语句
        // isValid = isValid || judgePoint24([...newNums, n1 + n2]);
        // isValid = isValid || judgePoint24([...newNums, n1 - n2]);
        // isValid = isValid || judgePoint24([...newNums, n2 - n1]);
        // isValid = isValid || judgePoint24([...newNums, n1 * n2]);

        if(card.size() == 1)
            return Math.abs(card.get(0) - 24) <= 0.00001;

        for(int i = 0; i < card.size(); i++){
            // j = i + 1 即不能重复添加
            for(int j = i + 1; j < card.size(); j++){
                List<Double> copy = new ArrayList<Double>(card);
                double b = copy.remove(j);
                double a = copy.remove(i);
                boolean valid = false;
                copy.add(a + b);
                valid |= solve(copy);
                // 直接覆盖，不用remove
                copy.set(copy.size() - 1, a - b);
                valid |= solve(copy);
                copy.set(copy.size() - 1, a * b);
                valid |= solve(copy);
                copy.set(copy.size() - 1, a / b);
                valid |= solve(copy);
                copy.set(copy.size() - 1, b - a);
                valid |= solve(copy);
                copy.set(copy.size() - 1, b / a);
                valid |= solve(copy);
                if(valid)
                    return true;
            }
        }

        return false;
    }
}
