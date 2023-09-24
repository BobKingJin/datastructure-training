package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author BobKing
 * @create 2021-09-11 7:37
 */
public class Number60 {

    // 记录数字是否使用过
    private boolean[] used;

    // 所求排列一定在叶子节点处得到，进入每一个分支，可以根据已经选定的数的个数，进而计算还未选定的数的个数，然后计算阶乘
    // 就知道这一个分支的叶子节点的个数，所以需要阶乘数组
    // 阶乘数组
    private int[] factorial;

    private int n;
    private int k;

    // 参考：https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
    public String getPermutation1(int n, int k) {

        this.n = n;
        this.k = k;
        calculateFactorial(n);

        // 查找全排列需要的布尔数组
        used = new boolean[n + 1];
        Arrays.fill(used, false);

        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }

    private void dfs(int index, StringBuilder path) {

        // 递归结束条件
        // 所求排列一定在叶子节点处得到
        if (index == n)
            return;

        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int count = factorial[n - index - 1];
        for (int i = 1; i <= n; i++) {

            if (used[i])
                continue;

            // 相当于跳过了
            if (count < k) {
                k -= count;
                continue;
            }

            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「来到叶子结点」，没有回头的过程
            // 在前面已经进行了 count < k 的判断，可以保证每一次选择都是正确的，所有不用回溯
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试
            return;
        }
    }

    // 计算阶乘数组
    private void calculateFactorial(int n) {

        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++)
            factorial[i] = factorial[i - 1] * i;
    }

    // 参考：https://leetcode-cn.com/problems/permutation-sequence/solution/hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
    // 方法一用的是减法，方法二用除法来确定角标
    public String getPermutation2(int n, int k) {

        // 注意：相当于在 n 个数字的全排列中找到下标为 k - 1 的那个数，因此 k 先减 1
        k--;

        int[] factorial = new int[n];
        factorial[0] = 1;
        // 先算出所有的阶乘值
        for (int i = 1; i < n; i++)
            factorial[i] = factorial[i - 1] * i;

        // 这里使用数组或者链表都行
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            nums.add(i);

        StringBuilder sb = new StringBuilder();

        // i 表示剩余的数字个数，初始化为 n - 1
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorial[i];
            sb.append(nums.remove(index));
            k -= index * factorial[i];
        }

        return sb.toString();
    }


}
