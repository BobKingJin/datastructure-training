package com.bobking.leetcode.training;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Number332 {

    // 参考：https://leetcode.cn/problems/reconstruct-itinerary/solution/javadfsjie-fa-by-pwrliang/
    public List<String> findItinerary(List<List<String>> tickets) {

        // 因为逆序插入，所以用链表
        List<String> ans = new LinkedList<String>();
        if (tickets == null || tickets.size() == 0)
            return ans;

        // 使用 Map<String, List<String>> 存储图，Key 为顶点，List<String> 为临接点
        // 为了避免存在环路导致节点重复访问，每访问过一条边就把它标记为访问过，或者直接将访问过的边删除
        // 为了实现按照字典顺序访问，把每个顶点的临接点按照字典顺序排序。这里，直接将访问过的边删除
        // 然后每次都取临接点的第一个即可满足字典顺序访问
        Map<String, List<String>> graph = new HashMap<String, List<String>>();
        for (List<String> pair : tickets) {
            // 因为涉及删除操作，用链表
            List<String> nbr = graph.computeIfAbsent(pair.get(0), k -> new LinkedList<String>());
            nbr.add(pair.get(1));
        }

        // 按目的顶点排序
        graph.values().forEach(x -> x.sort(String::compareTo));
        visit(graph, "JFK", ans);
        return ans;
    }

    // 采用 DFS 方式遍历图时，需要将访问到的节点  逆序  插入到结果集
    // 因此第一个访问到的节点将出现在结果集最后面，以顺序的方式来查看结果。如果第一个访问的节点是 “孤岛节点”，
    // 会出现在结果集的最后。当顺序读取结果集时，这种 “孤岛节点” 是最后遇到的，是图遍历的终点，这样就没有问题了
    // DFS方式遍历图，当走到不能走为止，再将节点加入到答案
    private void visit(Map<String, List<String>> graph, String src, List<String> ans) {

        List<String> nbr = graph.get(src);
        while (nbr != null && nbr.size() > 0) {
            String dest = nbr.remove(0);
            visit(graph, dest, ans);
        }

        ans.add(0, src); // 逆序插入
    }
}
