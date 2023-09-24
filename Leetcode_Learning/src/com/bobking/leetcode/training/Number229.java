package com.bobking.leetcode.training;

import java.util.*;

public class Number229 {

    List<Integer> res = new LinkedList<Integer>();

    // 参考：程序猿代码指南P372
    // 可以直接根据该思路改写，并不需要调用 getRes(int[] arr, int n, int k)方法
    public List<Integer> majorityElement1(int[] nums) {

        if (nums == null || nums.length == 0)
            return res;

        getRes(nums, nums.length, 3);
        return res;
    }

    // 每次删除 k 个最后剩下的数可能就是结果
    private void getRes(int[] arr, int n, int k) {

        if (arr == null || arr.length == 0 || n / k < 0)
            return;

        HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                if (cands.size() == k - 1) {
                    allMinus(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }

        HashMap<Integer, Integer> reals = getReal(arr, cands);
        for (Map.Entry<Integer, Integer> set : reals.entrySet()) {

            int key = set.getKey();
            int value = set.getValue();
            if (value > (n / k))
                res.add(key);
        }
    }

    private HashMap<Integer, Integer> getReal(int[] arr, HashMap<Integer, Integer> cands) {

        HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                if (reals.containsKey(curNum)) {
                    reals.put(curNum, reals.get(arr[i]) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }

    private void allMinus(HashMap<Integer, Integer> map) {

        List<Integer> removeList = new LinkedList<Integer>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == 1)
                removeList.add(entry.getKey());
            map.put(key, value - 1);
        }

        for (Integer removeKey : removeList)
            map.remove(removeKey);
    }

    // 同程序猿代码指南P372思路
    // 参考：https://leetcode-cn.com/problems/majority-element-ii/solution/liang-fu-dong-hua-yan-shi-mo-er-tou-piao-fa-zui-zh/
    public List<Integer> majorityElement2(int[] nums) {

        // 创建返回值
        List<Integer> res = new ArrayList<Integer>();

        if (nums == null || nums.length == 0)
            return res;

        // 初始化两个候选人candidate，和它们的计票
        int cand1 = nums[0];
        int count1 = 0;
        int cand2 = nums[0];
        int count2 = 0;

        // 摩尔投票法，分为两个阶段：配对阶段和计数阶段
        // 配对阶段
        for (int num : nums) {
            // 投票
            if (cand1 == num) {
                count1++;
                continue;
            }
            if (cand2 == num) {
                count2++;
                continue;
            }

            // 第 1 个候选人配对
            if (count1 == 0) {
                cand1 = num;
                count1++;
                continue;
            }
            // 第 2 个候选人配对
            if (count2 == 0) {
                cand2 = num;
                count2++;
                continue;
            }
            // 即出现第三个不同的数
            count1--;
            count2--;
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num){
                count1++;
            } else if (cand2 == num){
                count2++;
            }
        }

        if (count1 > nums.length / 3)
            res.add(cand1);
        if (count2 > nums.length / 3)
            res.add(cand2);

        return res;
    }

}
