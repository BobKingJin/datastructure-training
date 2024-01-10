package com.bobking.leetcode.training;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 2023/12/22 8:34
 * @Author: BobKing
 * @Description:
 */
public class Number2454 {

    // 参考: https://leetcode.cn/problems/next-greater-element-iv/solutions/1935877/by-endlesscheng-q6t5/?envType=daily-question&envId=2023-12-22
    public int[] secondGreaterElement(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        List<Integer> s = new ArrayList<Integer>();
        List<Integer> t = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!t.isEmpty() && nums[t.get(t.size() - 1)] < x) {
                // t 栈顶的下下个更大元素是 x
                ans[t.get(t.size() - 1)] = x;
                t.remove(t.size() - 1);
            }

            int j = s.size();
            while (j > 0 && nums[s.get(j - 1)] < x)
                // s 栈顶的下一个更大元素是 x
                j--;

            List<Integer> popped = s.subList(j, s.size());
            // 把从 s 弹出的这一整段元素加到 t
            t.addAll(popped);
            // 弹出一整段元素
            popped.clear();
            // 当前元素(的下标)加到 s 栈顶
            s.add(i);
        }
        return ans;
    }
}
