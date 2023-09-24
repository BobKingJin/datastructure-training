package com.bobking.leetcode.training;

public class Number299 {

    // 参考：https://leetcode.cn/problems/bulls-and-cows/solution/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-tdhs/
    public String getHint(String secret, String guess) {

        // 根据题意，可以对 secret 和 guess 进行诸位比较，统计公牛数量 a 和奶牛数量 b
        // 对于字符相同的位置，可以直接对 a 进行自增
        // 对于字符不同的位置，使用「哈希表」进行分别统计 secret 和 guess 的词频
        // 某个数字 x 在两者词频中的较小值，即为该数字对应的奶牛数量，统计所有数字 [0, 9] 的奶牛数量总和即为 b

        int n = secret.length();
        int a = 0;
        int b = 0;
        int[] cnt1 = new int[10];
        int[] cnt2 = new int[10];

        for (int i = 0; i < n; i++) {
            int c1 = secret.charAt(i) - '0';
            int c2= guess.charAt(i) - '0';
            if (c1 == c2) {
                a++;
            } else {
                cnt1[c1]++;
                cnt2[c2]++;
            }
        }

        for (int i = 0; i < 10; i++)
            b += Math.min(cnt1[i], cnt2[i]);

        return a + "A" + b + "B";
    }
}
