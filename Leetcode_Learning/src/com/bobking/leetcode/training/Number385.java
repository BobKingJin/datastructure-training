package com.bobking.leetcode.training;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Number385 {

    private interface Nested{

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();

    }

    private class NestedInteger implements Nested{

        // Constructor initializes an empty nested list.
        public NestedInteger(){

        }

        // Constructor initializes a single integer.
        public NestedInteger(int value){

        }

        @Override
        public boolean isInteger() {
            return false;
        }

        @Override
        public Integer getInteger() {
            return null;
        }

        @Override
        public void setInteger(int value) {

        }

        @Override
        public void add(NestedInteger ni) {

        }

        @Override
        public List<NestedInteger> getList() {
            return null;
        }
    }

    NestedInteger ph = new NestedInteger(0);

    // 参考：https://leetcode-cn.com/problems/mini-parser/solution/by-ac_oier-zuy6/
    public NestedInteger deserialize(String s) {

        Deque<NestedInteger> d = new ArrayDeque<NestedInteger>();
        char[] cs = s.toCharArray();
        int n = cs.length;
        int i = 0;
        while (i < n) {
            if (cs[i] == ',' && ++i >= 0)
                continue;

            if (cs[i] == '-' || (cs[i] >= '0' && cs[i] <= '9')) {

                int j = cs[i] == '-' ? i + 1 : i;
                int num = 0;

                while (j < n && (cs[j] >= '0' && cs[j] <= '9'))
                    num = num * 10 + (cs[j++] - '0');

                d.addLast(new NestedInteger(cs[i] == '-' ? -num : num));
                i = j;
            } else if (cs[i] == '[') {
                // 创建一个嵌套类型的 NestedInteger 实例并压入栈中
                // 同时为了方便，同时压入一个起「标识」作用的 NestedInteger 对象
                d.addLast(new NestedInteger());
                d.addLast(ph);
                i++;
            } else {

                // 从栈中取出元素，直到遇到起「标识」作用的 NestedInteger 对象（说明找到与当前 ] 成对的 [）
                // 将 [ 和 ] 之间的所有元素添加到 [ 所代指的嵌套 NestedInteger 实例中
                // 然后将 [ 所代指的嵌套 NestedInteger 实例重新放入栈中
                List<NestedInteger> list = new ArrayList<NestedInteger>();

                while (!d.isEmpty()) {
                    NestedInteger poll = d.pollLast();
                    if (poll == ph)
                        break;
                    list.add(poll);
                }

                for (int j = list.size() - 1; j >= 0; j--)
                    d.peekLast().add(list.get(j));

                i++;
            }
        }

        return d.peekLast();
    }

}
