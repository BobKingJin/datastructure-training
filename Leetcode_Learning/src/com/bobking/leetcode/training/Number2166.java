package com.bobking.leetcode.training;

import java.util.Arrays;

/**
 * @author BobKing
 * @create 2023-05-30 7:57
 */
public class Number2166 {

    class Bitset1 {

        // arr为正向掩膜
        int[] arr;
        // neg为反向掩膜
        int[] neg;
        // 记录 1 的个数有多少
        int cnt = 0;
        int size;

        public Bitset1(int size) {
            this.size = size;
            this.arr = new int[size];
            this.neg = new int[size];
            Arrays.fill(neg, 1);
        }

        public void fix(int idx) {
            if (arr[idx] == 0) {
                arr[idx] = 1;
                cnt++;
                neg[idx] = 0;
            }
        }

        public void unfix(int idx) {
            if (arr[idx] == 1) {
                arr[idx] = 0;
                cnt--;
                neg[idx] = 1;
            }
        }

        public void flip() {
            // 互换引用
            int[] t = arr;
            arr = neg;
            neg = t;
            // 反转后 1 的个数就变为了 size - cnt
            cnt = size - cnt;
        }

        public boolean all() {
            return cnt == size;
        }

        public boolean one() {
            return cnt >= 1;
        }

        public int count() {
            return cnt;
        }

        public String toString() {

            StringBuilder sb = new StringBuilder();

            for (int num : arr)
                sb.append(num);

            return sb.toString();
        }
    }

    class Bitset2 {
        int[] nums;
        int cnt;
        int f;

        public Bitset2(int size) {
            this.nums = new int[size];
            this.cnt = 0;
            this.f = 0;
        }

        public void fix(int idx) {
            if (nums[idx] == f) {
                nums[idx] = f ^ 1;
                cnt++;
            }
        }

        public void unfix(int idx) {
            if (nums[idx] == (f ^ 1)) {
                nums[idx] = f;
                cnt--;
            }
        }

        public void flip() {
            f ^= 1;
            cnt = nums.length - cnt;
        }

        public boolean all() {
            return cnt == nums.length;
        }

        public boolean one() {
            return cnt > 0;
        }

        public int count() {
            return cnt;
        }

        public String toString() {

            StringBuilder sb = new StringBuilder();

            for (int n : nums)
                sb.append(n ^ f);

            return sb.toString();
        }
    }
}
