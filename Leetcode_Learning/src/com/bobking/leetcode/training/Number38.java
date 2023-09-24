package com.bobking.leetcode.training;

/**
 * @author BobKing
 * @create 2022-03-28 15:48
 */
public class Number38 {

    // 参考：https://leetcode-cn.com/problems/count-and-say/solution/38-wai-guan-shu-lie-shuang-zhi-zhen-by-yiluolion/
    public String countAndSay1(int n) {

        // 首先定义变量 pre 记录前一项，初始化为空字符串
        // 定义变量 cur 记录当前项，初始化为 '1'
        StringBuffer pre = new StringBuffer("1");
        StringBuffer cur = new StringBuffer("1");
        for (int i = 1; i < n; i++) {

            pre = cur;
            cur = new StringBuffer();
            // 定义双指针 start， end 均指向序列项的头部，这里用于统计元素出现的次数
            int start = 0;
            int end = 0;
            // 开始遍历前一项，开始描述
            while (end < pre.length()) {

                // 从左往右遍历 pre，当元素相同时，移动 end 指针，直至元素不相同时，那么此时 end - start 就是相同元素的个数
                // 而 start 指针指向的元素就是重复的元素，进行拼接，cur += str(end - start) + pre[start]
                while (end < pre.length() && pre.charAt(start) == pre.charAt(end))
                    end++;

                // 元素出现次数与元素进行拼接
                cur = cur.append(Integer.toString(end - start)).append(pre.charAt(start));
                start = end;
            }
        }

        return cur.toString();
    }

    // 参考：https://leetcode-cn.com/problems/count-and-say/solution/gong-shui-san-xie-yi-ti-shuang-jie-mo-ni-pqv4/
    public String countAndSay2(int n) {
        
        String res = "1";

        for (int i = 2; i <= n; i++) {
            String cur = "";
            int m = res.length();
            for (int j = 0; j < m; ) {
                int k = j + 1;
                while (k < m && res.charAt(j) == res.charAt(k)) 
                    k++;
                int cnt = k - j;
                cur += cnt + "" + res.charAt(j);
                j = k;
            }
            res = cur;
        }

        return res;
    }

}
