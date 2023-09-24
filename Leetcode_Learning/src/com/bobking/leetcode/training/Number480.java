package com.bobking.leetcode.training;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Number480 {

    // 参考：https://leetcode-cn.com/problems/sliding-window-median/solution/feng-xian-dui-chong-shuang-dui-dui-ding-hq1dt/
    // 参考：程序猿代码指南P516
    public double[] medianSlidingWindow(int[] nums, int k) {

        double[] res = new double[nums.length - k + 1];
        // 大根堆
        PriorityQueue<Integer> big = new PriorityQueue<Integer>();
        // 小根堆
        PriorityQueue<Integer> small = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(b, a);
            }
        });

        HashMap<Integer, Integer> debt = new HashMap<Integer, Integer>();

        // balance记录了小值堆和大值堆之间数字的差值
        // i 为过期角标
        int i = 0;
        // j 为右移角标
        int j = k - 1;
        int index = 0;
        int balance = 0;

        int[] tmpArray = Arrays.copyOfRange(nums, 0, k);
        Arrays.sort(tmpArray);
        int scope = (k & 1) == 1 ? k / 2 : k / 2 - 1;

        for (int m = 0; m <= scope; m++)
            small.offer(tmpArray[m]);

        for (int m = scope + 1; m < k; m++)
            big.offer(tmpArray[m]);

        res[index++] = insertres(small, big, k);

        while (++j < nums.length) {
            balance += deleteElment(debt, nums, i++, small, big);
            balance += insertElment(nums, j, small, big);
            makeBalance(debt, small, big, balance);
            res[index++] = insertres(small, big, k);
            balance = 0;
        }

        return res;
    }

    private int deleteElment(HashMap<Integer, Integer> debt, int[] nums, int i, PriorityQueue<Integer> small, PriorityQueue<Integer> big) {
        int cur = nums[i];
        debt.put(cur, debt.getOrDefault(cur, 0) + 1);
        // nums[i] <= small.top()，就说明删掉的元素在 small 堆中
        return !small.isEmpty() && nums[i] <= small.peek() ? -1 : 1;
    }

    private int insertElment(int[] nums, int j, PriorityQueue<Integer> small, PriorityQueue<Integer> big) {
        // nums[j] <= small.top()，就应该让这个元素放到 samll 堆里面，balance++
        // 否则放到 big 堆里，balance--
        if (!small.isEmpty() && nums[j] <= small.peek()) {
            small.add(nums[j]);
            return 1;
        }

        big.add(nums[j]);
        return -1;
    }

    // balance 记录了此时两个堆不平等的情况，需要将其平衡到初始水平，此时如果是正的，就从小堆中删除，加到大堆里
    // 如果是负的，就反过来，平衡完之后，只需要对欠债元素进行删除就可。欠债元素必须先从 small 里进行删除，因为添加的时候也是优先添加到 small
    // 优先删除 big 中的元素极有可能导致 big 为空，从而导致添加中位数时出问题
    private void makeBalance(HashMap<Integer, Integer> debt, PriorityQueue<Integer> small, PriorityQueue<Integer> big, int balance) {

        if (balance > 0){
            big.offer(small.poll());
        } else if (balance < 0){
            small.offer(big.poll());
        }
        while (!small.isEmpty() && debt.getOrDefault(small.peek(), 0) > 0) {
            debt.put(small.peek(), debt.get(small.peek()) - 1);
            small.poll();
        }
        while (!big.isEmpty() && debt.getOrDefault(big.peek(), 0) > 0) {
            debt.put(big.peek(), debt.get(big.peek()) - 1);
            big.poll();
        }
    }

    private double insertres(PriorityQueue<Integer> small, PriorityQueue<Integer> big, int k) {
        return (k & 1) == 1 ? (double) small.peek() : ((double) small.peek() + (double) big.peek()) / 2;
    }
}
