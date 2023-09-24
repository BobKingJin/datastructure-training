package com.bobking.leetcode.training;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 * @author BobKing
 * @create 2023-03-19 0:10
 */
public class Number1625 {

    public String findLexSmallestString(String s, int a, int b) {

        TreeSet<String> visited = new TreeSet<String>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(s);
        visited.add(s);

        while(!queue.isEmpty()) {
            String cur = queue.poll();
            char[] ch = cur.toCharArray();
            for(int i = 1; i < ch.length; i += 2)
                ch[i] = (char)((ch[i] - '0' + a) % 10 + '0');

            String str = new String(ch);
            if(!visited.contains(str)) {
                visited.add(str);
                queue.offer(str);
            }
            String sub1 = cur.substring(0, b);
            String sub2 = cur.substring(b);
            String con = sub2 + sub1;
            if(!visited.contains(con)) {
                visited.add(con);
                queue.offer(con);
            }
        }
        return visited.first();
    }

}
