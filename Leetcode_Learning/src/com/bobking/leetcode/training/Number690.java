package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-09-03 11:30
 */
public class Number690 {

    private class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    Map<Integer, Employee> map = new HashMap<Integer, Employee>();

    // 参考：https://leetcode.cn/problems/employee-importance/solution/gong-shui-san-xie-yi-ti-shuang-jie-di-gu-s79x/
    public int getImportance1(List<Employee> employees, int id) {

        int n = employees.size();

        for (int i = 0; i < n; i++)
            map.put(employees.get(i).id, employees.get(i));

        return getVal(id);
    }

    private int getVal(int id) {

        Employee master = map.get(id);
        int ans = master.importance;

        for (int oid : master.subordinates) {
            Employee other = map.get(oid);
            ans += other.importance;
            for (int sub : other.subordinates)
                ans += getVal(sub);
        }

        return ans;
    }

    // 参考：https://leetcode.cn/problems/employee-importance/solution/gong-shui-san-xie-yi-ti-shuang-jie-di-gu-s79x/
    public int getImportance2(List<Employee> employees, int id) {

        int n = employees.size();
        Map<Integer, Employee> map = new HashMap<Integer, Employee>();
        for (int i = 0; i < n; i++)
            map.put(employees.get(i).id, employees.get(i));

        int ans = 0;

        Deque<Employee> d = new ArrayDeque<Employee>();
        d.addLast(map.get(id));

        while (!d.isEmpty()) {
            Employee poll = d.pollFirst();
            ans += poll.importance;
            for (int oid : poll.subordinates)
                d.addLast(map.get(oid));
        }

        return ans;
    }

}
