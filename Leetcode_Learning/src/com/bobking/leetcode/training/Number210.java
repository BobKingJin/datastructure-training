package com.bobking.leetcode.training;

import java.util.*;

/**
 * @author BobKing
 * @create 2022-04-30 10:59
 */
public class Number210 {

    // 参考：https://leetcode-cn.com/problems/course-schedule-ii/solution/tuo-bu-pai-xu-shen-du-you-xian-bian-li-python-dai-/
    public int[] findOrder1(int numCourses, int[][] prerequisites) {

        if (numCourses <= 0)
            return new int[0];

        HashSet<Integer>[] adj = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++)
            adj[i] = new HashSet<Integer>();

        // [1, 0] 0 -> 1
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            adj[p[1]].add(p[0]);
            // 入度
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }

        int[] res = new int[numCourses];
        // 当前结果集列表里的元素个数，正好可以作为下标
        int count = 0;

        while (!queue.isEmpty()) {
            // 当前入度为 0 的结点
            Integer head = queue.poll();
            res[count] = head;
            count++;
            Set<Integer> successors = adj[head];
            for (Integer nextCourse : successors) {
                inDegree[nextCourse]--;
                // 马上检测该节点的入度是否为 0，如果为 0，马上加入队列
                if (inDegree[nextCourse] == 0)
                    queue.offer(nextCourse);
            }
        }

        // 如果结果集中的数量不等于结点的数量，就不能完成课程任务，这一点是拓扑排序的结论
        if (count == numCourses)
            return res;

        return new int[0];
    }

    // 参考：https://leetcode-cn.com/problems/course-schedule-ii/solution/tuo-bu-pai-xu-shen-du-you-xian-bian-li-python-dai-/
    public int[] findOrder2(int numCourses, int[][] prerequisites) {

        if (numCourses <= 0)
            // 连课程数目都没有，就根本没有办法完成练习了，根据题意应该返回空数组
            return new int[0];

        int plen = prerequisites.length;
        if (plen == 0) {
            // 没有有向边，则表示不存在课程依赖，任务一定可以完成
            int[] ret = new int[numCourses];
            for (int i = 0; i < numCourses; i++)
                ret[i] = i;

            return ret;
        }

        int[] marked = new int[numCourses];
        // 初始化有向图 begin
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++)
            graph[i] = new HashSet<>();

        // 初始化有向图 end
        // 有向图的 key 是前驱结点，value 是后继结点的集合
        for (int[] p : prerequisites)
            graph[p[1]].add(p[0]);

        // 使用 Stack 或者 List 记录递归的顺序，这里使用 Stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, marked, stack))
                // 注意方法的语义，如果图中存在环，表示课程任务不能完成，应该返回空数组
                return new int[0];
        }
        // 在遍历的过程中，一直 dfs 都没有遇到已经重复访问的结点，就表示有向图中没有环
        // 所有课程任务可以完成，应该返回 true
        // 下面这个断言一定成立，这是拓扑排序告诉我们的结论
        assert stack.size() == numCourses;
        int[] ret = new int[numCourses];
        // 想想要怎么得到结论，我们的 dfs 是一致将后继结点进行 dfs 的
        // 所以压在栈底的元素，一定是那个没有后继课程的结点
        // 那个没有前驱的课程，一定在栈顶，所以课程学习的顺序就应该是从栈顶到栈底
        // 依次出栈就好了
        for (int i = 0; i < numCourses; i++)
            ret[i] = stack.pop();

        return ret;
    }

    /**
     * 注意这个 dfs 方法的语义
     * <p>
     * i：前访问的课程结点
     * marked：如果 == 1 表示正在访问中，如果 == 2 表示已经访问完了
     * true：表示图中存在环，false 表示访问过了，不用再访问了
     */
    private boolean dfs(int i,
                        HashSet<Integer>[] graph,
                        int[] marked,
                        Stack<Integer> stack) {
        // 如果访问过了，就不用再访问了
        if (marked[i] == 1)
            // 从正在访问中，到正在访问中，表示遇到了环
            return true;

        if (marked[i] == 2)
            // 表示在访问的过程中没有遇到环，这个节点访问过了
            return false;

        // 走到这里，是因为初始化呢，此时 marked[i] == 0
        // 表示正在访问中
        marked[i] = 1;
        // 后继结点的集合
        HashSet<Integer> successorNodes = graph[i];
        for (Integer successor : successorNodes) {
            if (dfs(successor, graph, marked, stack))
                // 层层递归返回 true ，表示图中存在环
                return true;
        }
        // i 的所有后继结点都访问完了，都没有存在环，则这个结点就可以被标记为已经访问结束
        // 状态设置为 2
        marked[i] = 2;
        stack.add(i);
        // false 表示图中不存在环
        return false;
    }
}
