package com.bobking.leetcode.training;

public class Number738 {

    // 参考：https://leetcode.cn/problems/monotone-increasing-digits/solution/1111lei-jia-fa-by-wincss-zt83/
    public int monotoneIncreasingDigits1(int n) {

        int ones = 111111111;
        int res = 0;

        for (int i = 0; i < 9; i++) {

            while (res + ones > n)
                ones /= 10;

            res += ones;
            if (ones == 0)
                break;
        }

        return res;
    }

    // 参考：https://leetcode.cn/problems/monotone-increasing-digits/solution/jian-dan-tan-xin-shou-ba-shou-jiao-xue-k-a0mp/
    public int monotoneIncreasingDigits2(int n) {

        // 既然要尽可能的大，那么这个数从高位开始要尽可能地保持不变。那么找到从高到低第一个满足 str[i] > str[i + 1] 的位置
        // 然后把 str[i] - 1 ，再把后面的位置都变成 9 即可。例如：n = 1234321，res = 1233999
        // 但是由于减小了 str[i] 以后，可能不满足 str[i - 1] <= str[i] 了，n = 2333332，res  = 2299999
        // 注意到如果减小 str[i] 以后不满足 str[i - 1] <= str[i]，那么肯定有 str[i - 1] == str[i]
        // 此时就需要再 str[i - 1] - 1，递归地会处理到某个位置 idx，发现 str[idx] == str[idx + 1] == ... = str[i]
        // 然后只要 str[idx] - 1，然后后面都补上 99 即可

        char[] arr = (n + "").toCharArray();
        int max = -1;
        int idx = -1;

        for (int i = 0; i < arr.length - 1; i++) {

            if (max < arr[i]) {
                max = arr[i];
                idx = i;
            }

            if (arr[i] > arr[i + 1]) {
                arr[idx] -= 1;
                for (int j = idx + 1; j < arr.length; j++)
                    arr[j] = '9';

                break;
            }
        }

        return Integer.parseInt(new String(arr));
    }
}
