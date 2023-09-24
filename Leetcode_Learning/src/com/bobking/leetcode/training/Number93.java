package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Number93 {

    // 参考：https://leetcode-cn.com/problems/restore-ip-addresses/solution/hui-su-suan-fa-hua-tu-fen-xi-jian-zhi-tiao-jian-by/
    public List<String> restoreIpAddresses1(String s) {

        List<String> res = new ArrayList<String>();
        // 如果长度不够，不搜索
        if (s == null || s.length() < 4 || s.length() > 12)
            return res;

        Deque<String> path = new ArrayDeque<String>(4);
        int splitTimes = 0;
        // 注意 dfs 中的 splitTimes 参数
        dfs1(s, s.length(), splitTimes, 0, path, res);
        return res;
    }

    private void dfs1(String s, int len, int split, int index, Deque<String> path, List<String> res) {

        // 递归结束条件
        if (index == len) {
            if (split == 4)
                res.add(String.join(".", path));
            return;
        }

        // 剪枝
        // 看到剩下的不够了，就退出（剪枝），len - begin 表示剩余的还未分割的字符串的位数
        if (len - index < (4 - split) || len - index > 3 * (4 - split))
            return;

        // 每个 ip 段 3 位
        for (int i = 0; i < 3; i++) {

            // 跳出循环
            if (index + i >= len)
                break;

            int ipSegment = judgeIfIpSegment1(s, index, index + i);
            if (ipSegment != -1) {
                // 在判断是 ip 段的情况下，才去做截取
                path.addLast(ipSegment + "");
                // 注意角标的变化 split + 1，begin + i + 1
                dfs1(s, len, split + 1, index + i + 1, path, res);
                // 回溯
                path.removeLast();
            }
        }
    }

    // 判断 s 的子区间 [left, right] 是否能够成为一个 ip 段，判断的同时顺便把类型转了
    private int judgeIfIpSegment1(String s, int left, int right) {

        int len = right - left + 1;

        // 大于 1 位的时候，不能以 0 开头
        if (len > 1 && s.charAt(left) == '0')
            return -1;

        // 转成 int 类型
        int res = 0;
        for (int i = left; i <= right; i++)
            res = res * 10 + s.charAt(i) - '0';

        if (res > 255)
            return -1;

        return res;
    }

    // 参考：https://leetcode-cn.com/problems/restore-ip-addresses/solution/hui-su-suan-fa-hua-tu-fen-xi-jian-zhi-tiao-jian-by/
    public List<String> restoreIpAddresses2(String s) {

        List<String> res = new ArrayList<String>();

        if (s == null || s.length() > 12 || s.length() < 4)
            return res;

        Deque<String> path = new ArrayDeque<String>(4);
        dfs2(s, s.length(), 0, 4, path, res);
        return res;
    }

    // 需要一个变量记录剩余多少段还没被分割
    private void dfs2(String s, int len, int index, int residue, Deque<String> path, List<String> res) {

        if (index == len) {
            if (residue == 0)
                res.add(String.join(".", path));
            return;
        }

        for (int i = index; i < index + 3; i++) {

            if (i >= len)
                break;

            if (residue * 3 < len - i)
                continue;

            if (judgeIpSegment2(s, index, i)) {
                String currentIpSegment = s.substring(index, i + 1);
                path.addLast(currentIpSegment);

                dfs2(s, len, i + 1, residue - 1, path, res);
                path.removeLast();
            }
        }
    }

    private boolean judgeIpSegment2(String s, int left, int right) {

        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0')
            return false;

        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }


}
