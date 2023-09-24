package com.bobking.leetcode.training;

import java.util.ArrayList;

public class Jianzhi62 {

    // 参考：程序猿代码指南P50
    public int lastRemaining1(int n, int m) {

        if (n < 0 || m < 1)
            return -1;

        return 0;
    }

    // 参考：https://www.nowcoder.com/practice/f78a359491e64a50bce2d89cff857eb6?tpId=13&&tqId=11199&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
    // 参考：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
    public int lastRemaining2(int n, int m) {

        if (n < 0 || m < 1)
            return -1;

        if (n == 1)
            return 0;

        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < n; i++)
            al.add(i);

        int index = 0;
        while (al.size() != 1) {
            int removeIndex = (index + m - 1) % n;
            al.remove(removeIndex);
            index = removeIndex;
            n = n - 1;
        }

        return al.remove(0);
    }

    // 参考：https://www.nowcoder.com/practice/f78a359491e64a50bce2d89cff857eb6?tpId=13&&tqId=11199&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
    public int lastRemaining3(int n, int m) {

        if (n < 0 || m < 1)
            return -1;

        int[] array = new int[n];

        // 当 m = 1 时，即第一个删除的数是 0，所以这里 i = -1
        // 要删除位置的角标
        int index = -1;
        int step = 0;
        int count = n;
        // 跳出循环时将最后一个元素也设置为了 -1
        while (count > 0) {
            // 指向上一个被删除对象的下一个元素
            index++;
            // 注意：当 i >= n时，又从头开始
            if (index >= n)
                // 模拟环
                index = 0;
            if (array[index] == -1)
                // 跳过已被删除的对象
                continue;
            // 记录已走过的
            step++;
            // 找到待删除的对象
            if (step == m) {
                array[index] = -1;
                step = 0;
                count--;
            }
        }
        // 返回跳出循环时的 i，即最后一个被设置为 -1 的元素
        return index;
    }
}
