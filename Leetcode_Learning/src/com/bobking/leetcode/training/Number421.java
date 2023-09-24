package com.bobking.leetcode.training;

import java.util.HashSet;
import java.util.Set;

public class Number421 {

    // 参考：程序猿代码指南P324
    public int findMaximumXOR1(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int[] eor = new int[nums.length];
        eor[0] = nums[0];

        // 生成 eor 数组
        for (int i = 1; i < nums.length; i++)
            eor[i] = eor[i - 1] ^ nums[i];

        int max = Integer.MIN_VALUE;
        // i - j 范围内的异或值
        for (int j = 0; j < nums.length; j++) {
            // j 结尾
            for (int i = 0; i <= j; i++)
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
        }

        return max;
    }

    // 参考：程序猿代码指南P326
    public int findMaximumXOR2(int[] arr) {

        if (arr == null || arr.length == 0)
            return 0;

        Trie root = new Trie();
        root.add(0);
        int max = Integer.MIN_VALUE;
        int pre = 0;
        for (int i = 0; i < arr.length; i++) {
            pre = pre ^ arr[i];
            max = Math.max(root.maxXor(pre), max);
            root.add(pre);
        }

        return max;
    }

    private class TrieNode {
        public TrieNode[] map = new TrieNode[2];
    }

    private class Trie {

        private TrieNode root = new TrieNode();

        // 构建字典树
        public void add(int num) {

            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int path = ((num >> i) & 1);
                if (node.map[path] == null) {
                    node.map[path] = new TrieNode();
                }
                node = node.map[path];
            }
        }

        // 求此时最优解
        public int maxXor(int val) {

            TrieNode curNode = root;
            int path = 0;

            // 最佳路径为哪条
            int best = 0;
            int res = 0;

            // val在前缀树中行进，一位一位地处理，得到最大异或和
            for (int move = 31; move >= 0; move--) {
                // val中该处理的那一位
                path = (val >> move) & 1;

                // 前缀树中理想最佳路径
                best = move == 31 ? path : (path ^ 1);

                // 前缀树中实际最佳路径，即res的二进制表示中这一位该取何值
                best = curNode.map[best] != null ? best : (best ^ 1);

                // 用或操作收集每一位产生的结果
                res |= (path ^ best) << move;
                curNode = curNode.map[best];
            }

            return res;
        }
    }

    // 参考：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array/solution/li-yong-yi-huo-yun-suan-de-xing-zhi-tan-xin-suan-f/
    // 先确定高位，再确定低位（有点贪心算法的意思），才能保证这道题的最大性质
    // 一位接着一位去确定这个数位的大小
    // 利用性质： a ^ b = c ，则 a ^ c = b，且 b ^ c = a
    public int findMaximumXOR3(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int res = 0;
        int mask = 0;
        for (int i = 30; i >= 0; i--) {
            // 注意点1：注意保留前缀的方法，mask 是这样得来的
            // 用异或也是可以的 mask = mask ^ (1 << i);
            mask = mask | (1 << i);

            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                // 注意点2：这里使用 & ，保留前缀的意思（从高位到低位）
                set.add(num & mask);
            }

            // 这里先假定第 n 位为 1 ，前 n-1 位 res 为之前迭代求得
            int temp = res | (1 << i);
            for (Integer prefix : set) {
                if (set.contains(prefix ^ temp)) {
                    res = temp;
                    break;
                }
            }
        }

        return res;
    }


}
