package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author BobKing
 * @create 2023-05-24 7:46
 */
public class Interview17_21 {

    // 参考：https://leetcode.cn/problems/volume-of-histogram-lcci/solution/shuang-zhi-zhen-an-xing-qiu-jie-xiang-xi-d162/
    public int trap1(int[] height) {

        int sum = 0;
        for (int i = 0; i < height.length; i++)
            sum += height[i];

        int volume = 0;
        int high = 1;
        int size = height.length;
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            while (left <= right && height[left] < high)
                left++;

            while (left <= right && height[right] < high)
                right--;

            volume += right - left + 1;
            high++;
        }
        return volume - sum;
    }

    // 参考：https://leetcode.cn/problems/volume-of-histogram-lcci/solution/gong-shui-san-xie-yi-ti-si-jie-po-su-yu-sqadp/
    public int trap2(int[] height) {

        int n = height.length;
        int ans = 0;

        for (int i = 1; i < n - 1; i++) {

            int cur = height[i];

            // 获取当前位置的左边最大值
            int l = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0; j--)
                l = Math.max(l, height[j]);
            if (l <= cur)
                continue;

            // 获取当前位置的右边边最大值
            int r = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++)
                r = Math.max(r, height[j]);
            if (r <= cur)
                continue;

            // 计算当前位置可接的雨水
            ans += Math.min(l, r) - cur;
        }
        return ans;
    }

    // 参考：https://leetcode.cn/problems/volume-of-histogram-lcci/solution/gong-shui-san-xie-yi-ti-si-jie-po-su-yu-sqadp/
    public int trap3(int[] height) {

        int n = height.length;
        int ans = 0;
        // 由于预处理最值的时候，会直接访问到 height[0] 或者 height[n - 1]，因此要特判一下
        if (n == 0)
            return ans;

        // 预处理每个位置左边的最值
        int[] lm = new int[n];
        lm[0] = height[0];
        for (int i = 1; i < n; i++)
            lm[i] = Math.max(height[i], lm[i - 1]);

        // 预处理每个位置右边的最值
        int[] rm = new int[n];
        rm[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--)
            rm[i] = Math.max(height[i], rm[i + 1]);

        for (int i = 1; i < n - 1; i++)
            ans += Math.min(lm[i], rm[i]) - height[i];

        return ans;
    }

    // 参考：https://leetcode.cn/problems/volume-of-histogram-lcci/solution/gong-shui-san-xie-yi-ti-si-jie-po-su-yu-sqadp/
    public int trap4(int[] height) {

        int n = height.length;
        int ans = 0;
        Deque<Integer> d = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {

            while (!d.isEmpty() && height[i] > height[d.peekLast()]) {

                int cur = d.pollLast();

                // 如果栈内没有元素，说明当前位置左边没有比其高的柱子，跳过
                if (d.isEmpty())
                    continue;

                // 左右位置，并有左右位置得出「宽度」和「高度」
                int l = d.peekLast();
                int r = i;
                int w = r - l + 1 - 2;
                int h = Math.min(height[l], height[r]) - height[cur];
                ans += w * h;
            }
            d.addLast(i);
        }
        return ans;
    }

}
